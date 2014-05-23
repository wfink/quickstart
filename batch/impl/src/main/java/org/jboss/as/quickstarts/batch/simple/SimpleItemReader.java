/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2014, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.jboss.as.quickstarts.batch.simple;

import java.io.Serializable;
import java.util.ArrayList;

import javax.batch.api.BatchProperty;
import javax.batch.api.chunk.AbstractItemReader;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;

import org.jboss.logging.Logger;

/**
 * This simple example use the @link{AbstractItemRead} to have a default implementation for not used methods.
 * 
 * @author "Wolf-Dieter Fink"
 * 
 */
public class SimpleItemReader extends AbstractItemReader {
    private final Logger LOG = Logger.getLogger(SimpleItemReader.class);
    private final ArrayList<String> items = new ArrayList<String>();
    private int itemCount = 0;

    /**
     * Control the number of Items from the job and reader properties
     */
    @Inject
    @BatchProperty(name = "noOfItems")
    String noOfItems;

    /**
     * Override number of items by JobOperator start properties.
     */
    @Inject
    @BatchProperty(name = "noOfItemsFromStart")
    String noOfItemsFromStart;

    /**
     * Only to show how a system property is injected
     */
    @Inject
    @BatchProperty(name = "javaVersion")
    String javaVersion;

    @Inject
    JobContext jobContext;

    /**
     * Initialize the chunk reading
     */
    @Override
    public void open(Serializable checkpoint) throws Exception {
        LOG.info("From system property 'java.version' we run on " + javaVersion);
        LOG.info("Initialize with noOfItems:" + noOfItems + " noOfItemsFromStart:" + noOfItemsFromStart);
        LOG.info("JobContext.properties  " + jobContext.getProperties()); // TODO Empty why????
        
        // prefer the item count which is given from the job start
        int itemsSize = Integer.parseInt(noOfItems);
        if(noOfItemsFromStart != null) {
            int items = Integer.parseInt(noOfItemsFromStart);
            if(items > 0) {
                itemsSize = items;
            }
        }
        
        // create the list of items
        for (int i = 1; i <= itemsSize; i++) {
            items.add("Item #" + i);
        }
    }

    /**
     * read one item for processing
     */
    @Override
    public Object readItem() throws Exception {
        if (itemCount < items.size()) {
            LOG.info("read item#" + (itemCount + 1));
            String item = items.get(itemCount);
            itemCount++;
            return item;
        } else {
            LOG.info("no more items");
            // no more items, job will end
            return null;
        }
    }
}
