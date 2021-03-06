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

package io.vertx.groovy.core.http;
import groovy.transform.CompileStatic
import io.vertx.lang.groovy.InternalHelper
import io.vertx.core.json.JsonObject
import io.vertx.groovy.core.MultiMap
import io.vertx.groovy.core.buffer.Buffer
import io.vertx.core.http.HttpVersion
import io.vertx.core.http.HttpMethod
import io.vertx.groovy.core.streams.ReadStream
import io.vertx.core.Handler
import io.vertx.groovy.core.net.SocketAddress
import io.vertx.groovy.core.net.NetSocket
/**
 * Represents a server-side HTTP request.
 * <p>
 * Instances are created for each request and passed to the user via a handler.
 * <p>
 * Each instance of this class is associated with a corresponding {@link io.vertx.groovy.core.http.HttpServerResponse} instance via
 * {@link io.vertx.groovy.core.http.HttpServerRequest#response}.<p>
 * It implements {@link io.vertx.groovy.core.streams.ReadStream} so it can be used with
 * {@link io.vertx.groovy.core.streams.Pump} to pump data with flow control.
 * <p>
*/
@CompileStatic
public class HttpServerRequest implements ReadStream<Buffer> {
  private final def io.vertx.core.http.HttpServerRequest delegate;
  public HttpServerRequest(Object delegate) {
    this.delegate = (io.vertx.core.http.HttpServerRequest) delegate;
  }
  public Object getDelegate() {
    return delegate;
  }
  public HttpServerRequest exceptionHandler(Handler<Throwable> handler) {
    ((io.vertx.core.http.HttpServerRequest) delegate).exceptionHandler(handler);
    return this;
  }
  public HttpServerRequest handler(Handler<Buffer> handler) {
    ((io.vertx.core.http.HttpServerRequest) delegate).handler(handler != null ? new Handler<io.vertx.core.buffer.Buffer>(){
      public void handle(io.vertx.core.buffer.Buffer event) {
        handler.handle(InternalHelper.safeCreate(event, io.vertx.groovy.core.buffer.Buffer.class));
      }
    } : null);
    return this;
  }
  public HttpServerRequest pause() {
    ((io.vertx.core.http.HttpServerRequest) delegate).pause();
    return this;
  }
  public HttpServerRequest resume() {
    ((io.vertx.core.http.HttpServerRequest) delegate).resume();
    return this;
  }
  public HttpServerRequest endHandler(Handler<Void> endHandler) {
    ((io.vertx.core.http.HttpServerRequest) delegate).endHandler(endHandler);
    return this;
  }
  /**
   * @return the HTTP version of the request
   */
  public HttpVersion version() {
    def ret = delegate.version();
    return ret;
  }
  /**
   * @return the HTTP method for the request.
   */
  public HttpMethod method() {
    def ret = delegate.method();
    return ret;
  }
  /**
   * @return the HTTP method as sent by the client
   */
  public String rawMethod() {
    def ret = delegate.rawMethod();
    return ret;
  }
  /**
   * @return true if this {@link io.vertx.groovy.core.net.NetSocket} is encrypted via SSL/TLS
   */
  public boolean isSSL() {
    def ret = delegate.isSSL();
    return ret;
  }
  /**
   * @return the scheme of the request
   */
  public String scheme() {
    def ret = delegate.scheme();
    return ret;
  }
  /**
   * @return the URI of the request. This is usually a relative URI
   */
  public String uri() {
    def ret = delegate.uri();
    return ret;
  }
  /**
   * @return The path part of the uri. For example /somepath/somemorepath/someresource.foo
   */
  public String path() {
    def ret = delegate.path();
    return ret;
  }
  /**
   * @return the query part of the uri. For example someparam=32&amp;someotherparam=x
   */
  public String query() {
    def ret = delegate.query();
    return ret;
  }
  /**
   * @return the request host. For HTTP2 it returns the  pseudo header otherwise it returns the  header
   */
  public String host() {
    def ret = delegate.host();
    return ret;
  }
  /**
   * @return the response. Each instance of this class has an {@link io.vertx.groovy.core.http.HttpServerResponse} instance attached to it. This is used to send the response back to the client.
   */
  public HttpServerResponse response() {
    if (cached_0 != null) {
      return cached_0;
    }
    def ret = InternalHelper.safeCreate(delegate.response(), io.vertx.groovy.core.http.HttpServerResponse.class);
    cached_0 = ret;
    return ret;
  }
  /**
   * @return the headers in the request.
   */
  public MultiMap headers() {
    if (cached_1 != null) {
      return cached_1;
    }
    def ret = InternalHelper.safeCreate(delegate.headers(), io.vertx.groovy.core.MultiMap.class);
    cached_1 = ret;
    return ret;
  }
  /**
   * Return the first header value with the specified name
   * @param headerName the header name
   * @return the header value
   */
  public String getHeader(String headerName) {
    def ret = delegate.getHeader(headerName);
    return ret;
  }
  /**
   * @return the query parameters in the request
   */
  public MultiMap params() {
    if (cached_2 != null) {
      return cached_2;
    }
    def ret = InternalHelper.safeCreate(delegate.params(), io.vertx.groovy.core.MultiMap.class);
    cached_2 = ret;
    return ret;
  }
  /**
   * Return the first param value with the specified name
   * @param paramName the param name
   * @return the param value
   */
  public String getParam(String paramName) {
    def ret = delegate.getParam(paramName);
    return ret;
  }
  /**
   * @return the remote (client side) address of the request
   */
  public SocketAddress remoteAddress() {
    if (cached_3 != null) {
      return cached_3;
    }
    def ret = InternalHelper.safeCreate(delegate.remoteAddress(), io.vertx.groovy.core.net.SocketAddress.class);
    cached_3 = ret;
    return ret;
  }
  /**
   * @return the local (server side) address of the server that handles the request
   */
  public SocketAddress localAddress() {
    if (cached_4 != null) {
      return cached_4;
    }
    def ret = InternalHelper.safeCreate(delegate.localAddress(), io.vertx.groovy.core.net.SocketAddress.class);
    cached_4 = ret;
    return ret;
  }
  /**
   * @return the absolute URI corresponding to the the HTTP request
   */
  public String absoluteURI() {
    def ret = delegate.absoluteURI();
    return ret;
  }
  /**
   * Convenience method for receiving the entire request body in one piece.
   * <p>
   * This saves the user having to manually setting a data and end handler and append the chunks of the body until
   * the whole body received. Don't use this if your request body is large - you could potentially run out of RAM.
   * @param bodyHandler This handler will be called after all the body has been received
   * @return 
   */
  public HttpServerRequest bodyHandler(Handler<Buffer> bodyHandler) {
    delegate.bodyHandler(bodyHandler != null ? new Handler<io.vertx.core.buffer.Buffer>(){
      public void handle(io.vertx.core.buffer.Buffer event) {
        bodyHandler.handle(InternalHelper.safeCreate(event, io.vertx.groovy.core.buffer.Buffer.class));
      }
    } : null);
    return this;
  }
  /**
   * Get a net socket for the underlying connection of this request.
   * <p>
   * USE THIS WITH CAUTION!
   * <p>
   * Once you have called this method, you must handle writing to the connection yourself using the net socket,
   * the server request instance will no longer be usable as normal.
   * Writing to the socket directly if you don't know what you're doing can easily break the HTTP protocol.
   * @return the net socket
   */
  public NetSocket netSocket() {
    if (cached_5 != null) {
      return cached_5;
    }
    def ret = InternalHelper.safeCreate(delegate.netSocket(), io.vertx.groovy.core.net.NetSocket.class);
    cached_5 = ret;
    return ret;
  }
  /**
   * Call this with true if you are expecting a multi-part body to be submitted in the request.
   * This must be called before the body of the request has been received
   * @param expect true - if you are expecting a multi-part body
   * @return a reference to this, so the API can be used fluently
   */
  public HttpServerRequest setExpectMultipart(boolean expect) {
    delegate.setExpectMultipart(expect);
    return this;
  }
  /**
   * @return true if we are expecting a multi-part body for this request. See {@link io.vertx.groovy.core.http.HttpServerRequest#setExpectMultipart}.
   */
  public boolean isExpectMultipart() {
    def ret = delegate.isExpectMultipart();
    return ret;
  }
  /**
   * Set an upload handler. The handler will get notified once a new file upload was received to allow you to deal
   * with the file upload.
   * @param uploadHandler 
   * @return a reference to this, so the API can be used fluently
   */
  public HttpServerRequest uploadHandler(Handler<HttpServerFileUpload> uploadHandler) {
    delegate.uploadHandler(uploadHandler != null ? new Handler<io.vertx.core.http.HttpServerFileUpload>(){
      public void handle(io.vertx.core.http.HttpServerFileUpload event) {
        uploadHandler.handle(InternalHelper.safeCreate(event, io.vertx.groovy.core.http.HttpServerFileUpload.class));
      }
    } : null);
    return this;
  }
  /**
   * Returns a map of all form attributes in the request.
   * <p>
   * Be aware that the attributes will only be available after the whole body has been received, i.e. after
   * the request end handler has been called.
   * <p>
   * {@link io.vertx.groovy.core.http.HttpServerRequest#setExpectMultipart} must be called first before trying to get the form attributes.
   * @return the form attributes
   */
  public MultiMap formAttributes() {
    if (cached_6 != null) {
      return cached_6;
    }
    def ret = InternalHelper.safeCreate(delegate.formAttributes(), io.vertx.groovy.core.MultiMap.class);
    cached_6 = ret;
    return ret;
  }
  /**
   * Return the first form attribute value with the specified name
   * @param attributeName the attribute name
   * @return the attribute value
   */
  public String getFormAttribute(String attributeName) {
    def ret = delegate.getFormAttribute(attributeName);
    return ret;
  }
  /**
   * Upgrade the connection to a WebSocket connection.
   * <p>
   * This is an alternative way of handling WebSockets and can only be used if no websocket handlers are set on the
   * Http server, and can only be used during the upgrade request during the WebSocket handshake.
   * @return the WebSocket
   */
  public ServerWebSocket upgrade() {
    def ret = InternalHelper.safeCreate(delegate.upgrade(), io.vertx.groovy.core.http.ServerWebSocket.class);
    return ret;
  }
  /**
   * Has the request ended? I.e. has the entire request, including the body been read?
   * @return true if ended
   */
  public boolean isEnded() {
    def ret = delegate.isEnded();
    return ret;
  }
  /**
   * Set a custom frame handler. The handler will get notified when the http stream receives an custom HTTP/2
   * frame. HTTP/2 permits extension of the protocol.
   * @param handler 
   * @return a reference to this, so the API can be used fluently
   */
  public HttpServerRequest customFrameHandler(Handler<HttpFrame> handler) {
    delegate.customFrameHandler(handler != null ? new Handler<io.vertx.core.http.HttpFrame>(){
      public void handle(io.vertx.core.http.HttpFrame event) {
        handler.handle(InternalHelper.safeCreate(event, io.vertx.groovy.core.http.HttpFrame.class));
      }
    } : null);
    return this;
  }
  /**
   * @return the {@link io.vertx.groovy.core.http.HttpConnection} associated with this request
   */
  public HttpConnection connection() {
    if (cached_7 != null) {
      return cached_7;
    }
    def ret = InternalHelper.safeCreate(delegate.connection(), io.vertx.groovy.core.http.HttpConnection.class);
    cached_7 = ret;
    return ret;
  }
  private HttpServerResponse cached_0;
  private MultiMap cached_1;
  private MultiMap cached_2;
  private SocketAddress cached_3;
  private SocketAddress cached_4;
  private NetSocket cached_5;
  private MultiMap cached_6;
  private HttpConnection cached_7;
}
