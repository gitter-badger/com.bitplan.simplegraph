/**
 * Copyright (c) 2018 BITPlan GmbH
 *
 * http://www.bitplan.com
 *
 * This file is part of the Opensource project at:
 * https://github.com/BITPlan/com.bitplan.simplegraph
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bitplan.simplegraph.core;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Stream;

import org.apache.tinkerpop.gremlin.structure.Vertex;

/**
 * The SimpleNode interface represents a Vertex in a graph
 * https://en.wikipedia.org/wiki/Vertex_(graph_theory) it wraps an Apache
 * TinkerPop vertex
 * 
 * @author wf
 *
 */
public interface SimpleNode extends SimpleGraph {
  static Consumer<Vertex> printDebug = 
    vertex->vertex.properties().forEachRemaining(prop->System.out.println(String.format("%s.%s=%s",vertex.label(),prop.label(),prop.value())));

  static Consumer<Map<?, Object>> printMapDebug = map -> {
    map.keySet().forEach(
        key -> System.out.println(String.format("%s=%s", key, map.get(key))));
  };

public static enum EdgeDirection {
  IN, OUT
  // ,BOTH
  };

  // interface to Tinkertop/Gremlin wrapped Vertex
  public Vertex getVertex();

  public Vertex setVertex(Vertex vertex);

  public void setMap(Map<String, Object> map);

  // fill my Vertex using my Map - this is the standard way to set the Nodes
  // Vertex
  public Vertex setVertexFromMap();

  public Map<String, Object> getMap();

  // fill the map with it's data
  public Map<String, Object> initMap();

  public Stream<SimpleNode> out(String edgeName);

  public Stream<SimpleNode> in(String edgeName);

  /**
   * set the property value for the given key
   * with traditional java style syntax
   * 
   * @param key
   * @param value
   */
  public default void setProperty(String key,Object value) {
    property(key,value);
  }
  
  /**
   * get the property value for the given key
   * with traditional java style syntax
   * 
   * @param key
   * @return - the property value
   */
  public default Object getProperty(String key) {
    return property(key);  
  }
  
  /**
   * add the key value pair to the vertex and map
   * tinkerpop style syntax
   * 
   * @param key
   * @param value
   */
  public default void property(String key, Object value) {
    getVertex().property(key, value);
    getMap().put(key, value);
  }
  
  /**
   * tinkerpop compatible getter
   * @param key
   * @return - the property
   */
  public default Object property(String key) {
    return getMap().get(key);
  }

  // interfaces with default implementation
  /**
   * recursive out handling
   * 
   * @param edgeName
   * @param recursionDepth
   * @return
   */
  public default Stream<SimpleNode> recursiveOut(String edgeName,
      int recursionDepth) {
    // get the neighbor nodes with wrapped vertices following the edge with the
    // given name
    // prepare a list of simple nodes for the recursive results
    List<SimpleNode> recursiveOuts = new ArrayList<SimpleNode>();
    Stream<SimpleNode> outs = this.out(edgeName);
    // if we still have recursion levels left over
    if (recursionDepth > 0) {
      outs.forEach(simpleNode -> {
        recursiveOuts.add(simpleNode);
        // get the edge nodes for this level
        Stream<SimpleNode> levelOuts = simpleNode.recursiveOut(edgeName,
            recursionDepth - 1);
        // add them all to the recursive result
        levelOuts
            .forEach(levelSimpleNode -> recursiveOuts.add(levelSimpleNode));
      });
    }
    return recursiveOuts.stream();
  }

  // show name values
  public default void printNameValues(PrintStream out) {
    Map<String, Object> map = this.getMap();
    for (String key : map.keySet()) {
      out.println(String.format("%s = %s", key, map.get(key)));
    }
  }

  /**
   * forAll
   */
  public default void forAll(Consumer<Vertex> consumer) {
    g().V().forEachRemaining(consumer);
  };

}