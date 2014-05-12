/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014, Red Hat, Inc. and/or its affiliates, and individual
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
package org.jboss.as.quickstarts.batch.simple;

import javax.annotation.PostConstruct;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import org.jboss.logging.Logger;

/**
 * A simple JSF controller to start the batch process for a simple job.
 * 
 * @author <a href="mailto:wfink@redhat.com">Wolf-Dieter Fink</a>
 */
@Model
public class SimpleBatchController {
    private static final Logger LOOGER = Logger.getLogger(SimpleBatchController.class);
    private SimpleBatchParameter job;

  /**
   * Initialize the controller.
   */
    @PostConstruct
    public void initForm() {
        this.job = new SimpleBatchParameter();
    }

    @Produces
    @Named
    public SimpleBatchParameter getJob() {
        return this.job;
    }

    public void startSimpleBatch() {
        LOOGER.info("Try to start the 'simpleJob'");
        
        // get the global operator for batch
        JobOperator operator = BatchRuntime.getJobOperator();
        
        long jobId = operator.start("simpleJob", this.job.getJobProperties());
        this.job.setJobId(String.valueOf(jobId));
    }
}
