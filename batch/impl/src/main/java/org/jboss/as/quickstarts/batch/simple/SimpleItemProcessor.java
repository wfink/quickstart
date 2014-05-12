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

import javax.batch.api.chunk.ItemProcessor;
import javax.inject.Named;

import org.jboss.logging.Logger;

/**
 * The item processor is to exact handle one Item read by the ItemReader.
 * 
 * @author Wolf-Dieter Fink
 */
public class SimpleItemProcessor implements ItemProcessor {
    private static final Logger LOG = Logger.getLogger(SimpleItemProcessor.class);
    
    /**
     * Transform the object read by the ItemReader to an Object which will be handled by ItemWriter.
     * Here we simple copy the String.
     */
    @Override
    public Object processItem(Object readedItem) throws Exception {
        LOG.info("process item : " + readedItem);
        return readedItem;
    }

}
