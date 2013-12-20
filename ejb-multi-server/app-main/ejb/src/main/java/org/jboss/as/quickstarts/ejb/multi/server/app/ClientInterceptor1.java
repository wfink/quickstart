/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2012, Red Hat, Inc., and individual contributors
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
package org.jboss.as.quickstarts.ejb.multi.server.app;

import org.jboss.ejb.client.EJBClientInterceptor;
import org.jboss.ejb.client.EJBClientInvocationContext;
import org.jboss.logging.Logger;

/**
 * @author <a href="mailto:wfink@redhat.com">Wolf-Dieter Fink</a>
 */
public class ClientInterceptor1 implements EJBClientInterceptor {
    private static final Logger LOGGER = Logger.getLogger(ClientInterceptor1.class);
    @Override
    public void handleInvocation(EJBClientInvocationContext context) throws Exception {
        LOGGER.fatal("Called before EJB invocation!");
        context.sendRequest();
    }

    @Override
    public Object handleInvocationResult(EJBClientInvocationContext context) throws Exception {
        LOGGER.fatal("Called after EJB invocation has returned!");
        return context.getResult();
    }

}
