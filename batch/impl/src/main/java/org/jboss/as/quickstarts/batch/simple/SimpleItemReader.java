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

import javax.batch.api.chunk.AbstractItemReader;
import javax.inject.Named;

import org.jboss.logging.Logger;

/**
 * This simple example use the @link{AbstractItemRead} to have a default implementation
 * for not used methods.
 * 
 * @author "Wolf-Dieter Fink"
 *
 */
@Named
public class SimpleItemReader extends AbstractItemReader {
    private final Logger LOG = Logger.getLogger(SimpleItemReader.class);
    private final ArrayList<String> items = new ArrayList<String>();
    private int itemCount = 0;
    
   
    @Override
    public void open(Serializable checkpoint) throws Exception {
        LOG.info("Initialize");
        if(items.isEmpty()) {
            for (int i = 1; i != 10; i++) {
                items.add("Item #"+i);
            }
        }
    }


    /* (non-Javadoc)
     * @see javax.batch.api.chunk.AbstractItemReader#readItem()
     */
    @Override
    public Object readItem() throws Exception {
        LOG.info("read item#"+itemCount);
        if(itemCount < items.size()) {
            String item = items.get(itemCount);
            itemCount++;
            return item;
        }else{
            return null;
        }
    }

}
