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

package org.jboss.as.quickstarts.batch.parallel;

import java.util.logging.Logger;

import javax.batch.api.Batchlet;
import javax.inject.Named;

/**
 * @author "Wolf-Dieter Fink"
 *
 */
@Named("ParallelStartBatchlet")
public class ParallelStartBatchlet implements Batchlet {
    private static final Logger LOG = Logger.getLogger(ParallelStartBatchlet.class.getName());  
    /* (non-Javadoc)
     * @see javax.batch.api.Batchlet#process()
     */
    @Override
    public String process() throws Exception {
        LOG.info("process() called");
        return null;
    }

    /* (non-Javadoc)
     * @see javax.batch.api.Batchlet#stop()
     */
    @Override
    public void stop() throws Exception {
        LOG.info("stop() called");
    }

}
