/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.logstash.logback.appender;

import ch.qos.logback.classic.net.LoggingEventPreSerializationTransformer;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.spi.PreSerializationTransformer;

public class LogstashTcpSocketAppender extends AbstractLogstashTcpSocketAppender<ILoggingEvent> {

    private static final PreSerializationTransformer<ILoggingEvent> PST = new LoggingEventPreSerializationTransformer();

    private boolean includeCallerData;

    @Override
    protected void prepareForDeferredProcessing(final ILoggingEvent event) {
        event.prepareForDeferredProcessing();
        if (includeCallerData) {
            event.getCallerData();
        }
    }

    public boolean isIncludeCallerData() {
        return includeCallerData;
    }

    public void setIncludeCallerData(boolean includeCallerData) {
        this.includeCallerData = includeCallerData;
    }

    /**
     * Get the pre-serialization transformer that will be used to transform each
     * event into a Serializable object before delivery to the remote receiver.
     *
     * @return transformer object
     */
    public PreSerializationTransformer<ILoggingEvent> getPST() {
        return PST;
    }
}