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
package com.bitplan.simplegraph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//import org.junit.Ignore;
import org.junit.Test;
import org.wikidata.wdtk.datamodel.interfaces.EntityDocument;
import org.wikidata.wdtk.datamodel.json.jackson.datavalues.JacksonValueItemId;
import org.wikidata.wdtk.wikibaseapi.WikibaseDataFetcher;
import org.wikidata.wdtk.wikibaseapi.apierrors.MediaWikiApiErrorException;

import com.bitplan.wikidata.WikiDataNode;
import com.bitplan.wikidata.WikiDataSystem;

/**
 * test access to WikiData toolkit
 * 
 * @author wf
 *
 */
public class TestWikiData extends BaseTest {

  private static SimpleNode queenVictoria;
  private static WikiDataSystem wikiDataSystem;

  @Test
  public void testWikiDatabaseFetcher() throws MediaWikiApiErrorException {
    // debug=true;
    WikibaseDataFetcher wbdf = WikibaseDataFetcher.getWikidataDataFetcher();
    if (debug)
      System.out.println("*** Fetching data for entity Q42:");
    EntityDocument q42 = wbdf.getEntityDocument("Q42");
    assertNotNull(q42);
    if (debug) {
      System.out.println(String.format("id: %s, rev: %s",
          q42.getEntityId().toString(), q42.getRevisionId()));
    }
    // <http://www.wikidata.org/entity/Q42 (item)>
    assertEquals("http://www.wikidata.org/entity/Q42 (item)",
        q42.getEntityId().toString());
  }

  /**
   * get the WikiData node for QueenVictoria as a SimpleNode
   * 
   * @return
   * @throws Exception
   */
  public static SimpleNode getQueenVictoria() throws Exception {
    if (queenVictoria == null) {
      wikiDataSystem = new WikiDataSystem();
      wikiDataSystem.connect();
      wikiDataSystem.useCache(new File("QueenVictoriaWikiData.xml"),"property");
      queenVictoria = wikiDataSystem.moveTo("Q9439");
    }
    return queenVictoria;
  }

  /**
   * test that we can lookup property names from a property cache
   * 
   * @throws Exception
   */
  @Test
  public void testPropertyCache() throws Exception {
    debug = true;
    queenVictoria = getQueenVictoria();
    queenVictoria.getVertex().properties().forEachRemaining(prop -> {
      SimpleNode propNode = wikiDataSystem.cache(prop.label(),false);
      if (debug) {
        if (propNode != null) {
          System.out.println(
              String.format("%s (%s)=%s", propNode.getMap().get("label_en"),
                  prop.label(), prop.value().toString()));
        }
      }
    });
    wikiDataSystem.flushCache("property");
  }

  @Test
  public void testQueenVictoria() throws Exception {
    debug = true;
    queenVictoria = getQueenVictoria();
    if (debug)
      queenVictoria.printNameValues(System.out);
    @SuppressWarnings("unchecked")
    List<JacksonValueItemId> children = (List<JacksonValueItemId>) queenVictoria
        .getMap().get("P40");
    assertEquals(9, children.size());
    assertEquals("Q9439",queenVictoria.getProperty("wikidata_id"));
    Object image=queenVictoria.getProperty("P18");
    assertEquals("Queen Victoria by Bassano.jpg",image); // image
    for (JacksonValueItemId child : children) {
      if (debug)
        System.out.println(child.getId());
      assertTrue(child.getId().startsWith("Q"));
    }
  }

  @Test
  public void testQueenVictoriaFather() throws Exception {
    //debug=true;
    queenVictoria = getQueenVictoria();
    // first try to navigate via Property Id P22 father
    List<SimpleNode> fatherList = queenVictoria.out("P22")
        .collect(Collectors.toCollection(ArrayList::new));
    assertEquals(1, fatherList.size());
    SimpleNode fatherNode = fatherList.get(0);
    if (debug)
      fatherNode.printNameValues(System.out);
    assertEquals(fatherNode.getMap().get("label_en"),
        "Prince Edward Augustus, Duke of Kent and Strathearn");
  }

  @Test
  public void testQueenVictoriaChildren() throws Exception {
    //debug=true;
    queenVictoria = getQueenVictoria();
    // first try to navigate via Property Id
    List<SimpleNode> childrenP40 = queenVictoria.out("P40")
        .collect(Collectors.toCollection(ArrayList::new));
    assertEquals(9, childrenP40.size());
    // then via property name
    List<SimpleNode> children = queenVictoria.out("child")
        .collect(Collectors.toCollection(ArrayList::new));
    assertEquals(9, children.size());
    if (debug) {
      children.forEach(child->child.printNameValues(System.out));
    }
  }

  @Test
  public void testLanguages() throws Exception {
    //debug = true;
    String[] languages = { "zh", "en", "es", "ar", "hi", "pt", "fr", "ja", "ru",
        "de" };
    WikiDataSystem lwikiDataSystem = new WikiDataSystem(languages);
    lwikiDataSystem.connect();
    // Beer
    SimpleNode beerNode = lwikiDataSystem.moveTo("Q44");
    for (String language : languages) {
      long wikiCount = beerNode.g().V().has("wiki_" + language).count().next()
          .longValue();
      assertEquals(1, wikiCount);
      WikiDataNode foundNode = (WikiDataNode) (beerNode.g().V()
          .has("wiki_" + language).next().property("mysimplenode").value());
      assertEquals(beerNode, foundNode);
      Map<String, Object> map = foundNode.getMap();
      if (debug)
        System.out.println(String.format("%s -> label: %s, wiki:%s", language,
            map.get("label_" + language), map.get("wiki_" + language)));
    }
    // if (debug)
    // beerNode.printNameValues(System.out);
  }

  @Test
  public void testMoveWithoutConnect() {
    WikiDataSystem lwikiDataSystem = new WikiDataSystem();
    Exception foundException = null;
    try {
      // Beer
      lwikiDataSystem.moveTo("Q44");
    } catch (Exception e) {
      foundException = e;
    }
    assertNotNull(foundException);
    assertTrue(foundException instanceof java.lang.IllegalStateException);
    assertEquals("not connected", foundException.getMessage());
  }

  @Test
  public void testUnknownEntityId() {
    wikiDataSystem = new WikiDataSystem();
    Exception foundException = null;
    try {
      wikiDataSystem.connect();
      queenVictoria = wikiDataSystem.moveTo("Q6");
    } catch (Exception e) {
      foundException = e;
    }
    assertNotNull(foundException);
    assertTrue(foundException instanceof java.lang.IllegalArgumentException);
    assertEquals("Entity Document for Q6 not found",
        foundException.getMessage());
  }

}
