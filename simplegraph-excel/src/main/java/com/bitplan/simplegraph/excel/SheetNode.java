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
package com.bitplan.simplegraph.excel;

import java.util.Map;
import java.util.stream.Stream;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.bitplan.simplegraph.core.Keys;
import com.bitplan.simplegraph.core.SimpleGraph;
import com.bitplan.simplegraph.core.SimpleNode;
import com.bitplan.simplegraph.impl.SimpleNodeImpl;

public class SheetNode extends SimpleNodeImpl {

  private XSSFSheet sheet;

  public SheetNode(SimpleGraph simpleGraph, String kind, String[] keys) {
    super(simpleGraph, kind, keys);
  }

  public SheetNode(SimpleGraph simpleGraph, XSSFSheet sheet) {
    this(simpleGraph,"sheet",Keys.EMPTY_KEYS);
    this.sheet=sheet;
    super.setVertexFromMap();
  }

  @Override
  public Map<String, Object> initMap() {
    map.put("sheetname", sheet.getSheetName());
    return map;
  }

  @Override
  public Stream<SimpleNode> out(String edgeName) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Stream<SimpleNode> in(String edgeName) {
    // TODO Auto-generated method stub
    return null;
  }

}