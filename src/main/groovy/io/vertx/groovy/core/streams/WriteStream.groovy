/*
 * Copyright 2014 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package io.vertx.groovy.core.streams;
import groovy.transform.CompileStatic
import io.vertx.lang.groovy.InternalHelper
import io.vertx.core.json.JsonObject
import io.vertx.core.Handler
/**
 *
 * Represents a stream of data that can be written to.
 * <p>
 * Any class that implements this interface can be used by a {@link io.vertx.groovy.core.streams.Pump} to pump data from a <code>ReadStream</code>
 * to it.
*/
@CompileStatic
public interface WriteStream<T> extends StreamBase {
  public Object getDelegate();
  WriteStream<T> exceptionHandler(Handler<Throwable> handler);
  WriteStream<T> write(T data);
  void end();
  void end(T t);
  WriteStream<T> setWriteQueueMaxSize(int maxSize);
  boolean writeQueueFull();
  WriteStream<T> drainHandler(Handler<Void> handler);
}

@CompileStatic
class WriteStreamImpl<T> implements WriteStream<T> {
  private final def io.vertx.core.streams.WriteStream delegate;
  public WriteStreamImpl(Object delegate) {
    this.delegate = (io.vertx.core.streams.WriteStream) delegate;
  }
  public Object getDelegate() {
    return delegate;
  }
  /**
   * Set an exception handler on the write stream.
   * @param handler the exception handler
   * @return a reference to this, so the API can be used fluently
   */
  public WriteStream<T> exceptionHandler(Handler<Throwable> handler) {
    ((io.vertx.core.streams.WriteStream) delegate).exceptionHandler(handler);
    return this;
  }
  /**
   * Write some data to the stream. The data is put on an internal write queue, and the write actually happens
   * asynchronously. To avoid running out of memory by putting too much on the write queue,
   * check the {@link io.vertx.groovy.core.streams.WriteStream#writeQueueFull} method before writing. This is done automatically if using a {@link io.vertx.groovy.core.streams.Pump}.
   * @param data the data to write
   * @return a reference to this, so the API can be used fluently
   */
  public WriteStream<T> write(T data) {
    ((io.vertx.core.streams.WriteStream) delegate).write(data != null ? InternalHelper.unwrapObject(data) : null);
    return this;
  }
  /**
   * Ends the stream.
   * <p>
   * Once the stream has ended, it cannot be used any more.
   */
  public void end() {
    ((io.vertx.core.streams.WriteStream) delegate).end();
  }
  /**
   * Same as {@link io.vertx.groovy.core.streams.WriteStream#end} but writes some data to the stream before ending.
   * @param t 
   */
  public void end(T t) {
    ((io.vertx.core.streams.WriteStream) delegate).end(t != null ? InternalHelper.unwrapObject(t) : null);
  }
  /**
   * Set the maximum size of the write queue to <code>maxSize</code>. You will still be able to write to the stream even
   * if there is more than <code>maxSize</code> items in the write queue. This is used as an indicator by classes such as
   * <code>Pump</code> to provide flow control.
   * <p/>
   * The value is defined by the implementation of the stream, e.g in bytes for a
   * {@link io.vertx.groovy.core.net.NetSocket}, the number of {@link io.vertx.groovy.core.eventbus.Message} for a
   * {@link io.vertx.groovy.core.eventbus.MessageProducer}, etc...
   * @param maxSize the max size of the write stream
   * @return a reference to this, so the API can be used fluently
   */
  public WriteStream<T> setWriteQueueMaxSize(int maxSize) {
    ((io.vertx.core.streams.WriteStream) delegate).setWriteQueueMaxSize(maxSize);
    return this;
  }
  /**
   * This will return <code>true</code> if there are more bytes in the write queue than the value set using {@link io.vertx.groovy.core.streams.WriteStream#setWriteQueueMaxSize}
   * @return true if write queue is full
   */
  public boolean writeQueueFull() {
    def ret = ((io.vertx.core.streams.WriteStream) delegate).writeQueueFull();
    return ret;
  }
  /**
   * Set a drain handler on the stream. If the write queue is full, then the handler will be called when the write
   * queue is ready to accept buffers again. See {@link io.vertx.groovy.core.streams.Pump} for an example of this being used.
   * <p/>
   * The stream implementation defines when the drain handler, for example it could be when the queue size has been
   * reduced to <code>maxSize / 2</code>.
   * @param handler the handler
   * @return a reference to this, so the API can be used fluently
   */
  public WriteStream<T> drainHandler(Handler<Void> handler) {
    ((io.vertx.core.streams.WriteStream) delegate).drainHandler(handler);
    return this;
  }
}
