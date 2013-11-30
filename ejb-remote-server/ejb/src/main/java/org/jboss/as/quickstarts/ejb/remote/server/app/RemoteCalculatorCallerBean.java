/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.as.quickstarts.ejb.remote.server.app;

import java.util.Hashtable;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jboss.as.quickstarts.ejb.remote.stateless.RemoteCalculator;
import org.jboss.logging.Logger;

/**
 * <p>The bean called by the standalone client.</p>
 * <p>The sub application, deployed in different servers is called direct with the outbound connection approach</p>
 * 
 * @author <a href="mailto:wfink@redhat.com">Wolf-Dieter Fink</a>
 */
@Stateless
public class RemoteCalculatorCallerBean implements RemoteCalculatorCaller {
    private static final Logger LOGGER = Logger.getLogger(RemoteCalculatorCallerBean.class);
    @Resource
    SessionContext context;

  /**
   * The context to invoke foreign EJB's as the SessionContext can not be used for that.
   */
    private InitialContext iCtx;

    @EJB(lookup = "ejb:/jboss-as-ejb-remote-server-side/CalculatorBean!org.jboss.as.quickstarts.ejb.remote.stateless.RemoteCalculator")
    RemoteCalculator remoteCalculatorProxy;

  /**
   * Initialize and store the context for the EJB invocations.
   */
    @PostConstruct
    public void init() {
        try {
            final Hashtable<String, String> p = new Hashtable<String, String>();
            p.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
            this.iCtx = new InitialContext(p);
        }catch (NamingException e) {
            throw new RuntimeException("Could not initialize context", e);
        }
    }

    @Override
    public void invokeRemoteCalculator() {
        LOGGER.info("Try to add 10+20 with the RemoteCalculator");
        int result = remoteCalculatorProxy.add(10, 20);
        LOGGER.info("Result from RemoteCalculator = " + result);
    }
}
