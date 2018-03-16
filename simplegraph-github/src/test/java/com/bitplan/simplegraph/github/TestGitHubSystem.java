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
package com.bitplan.simplegraph.github;

import java.util.logging.Logger;

import org.junit.Test;

import com.bitplan.simplegraph.core.SimpleNode;
import com.bitplan.simplegraph.impl.PropertiesImpl;
import com.bitplan.simplegraph.json.JsonSystem;


/**
 * test the GitHub System
 * 
 * @author wf
 *
 */
public class TestGitHubSystem {
  public static boolean debug = false;
  protected static Logger LOGGER = Logger
      .getLogger("com.bitplan.simplegraph.github");

  @Test
  public void testGitHubSystem() throws Exception {
    GitHubSystem ghs = new GitHubSystem();
    ghs.connect();
    ghs.moveTo("");
    ghs.forAll(SimpleNode.printDebug);
  }

  @Test
  public void testGitHubGraphQLApi() throws Exception {
    // https://developer.github.com/v4/explorer/
    // https://developer.github.com/v4/guides/forming-calls/#the-graphql-endpoint
    // https://developer.github.com/v4/guides/forming-calls/#example-query
    // https://api.github.com/graphql
    // https://stackoverflow.com/questions/49324611/github-v4-graphql-api-with-java-using-graphql-java
    debug=true;
    PropertiesImpl properties=new PropertiesImpl("github");
    String token=(String) properties.getProperty("oauth");
    
    JsonSystem js=new JsonSystem();
    js.setDebug(debug);
    js.connect("Authorization: bearer "+token);
    js.moveTo("https://api.github.com/graphql");
    js.forAll(SimpleNode.printDebug);
    js.getStartNode().g().V().hasLabel("fields").forEachRemaining(node->System.out.println(node.property("name").value().toString()));;
  }

}
