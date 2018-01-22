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
package com.bitplan.mediawiki;

import com.bitplan.mediawiki.japi.SSLWiki;
import com.bitplan.mediawiki.japi.SiteInfo;
import com.bitplan.simplegraph.SimpleNode;
import com.bitplan.simplegraph.SimpleSystem;
import com.bitplan.simplegraph.impl.SimpleSystemImpl;

/**
 * access to MediaWiki via API
 * @author wf
 *
 */
public class MediaWikiSystem extends SimpleSystemImpl implements SimpleSystem {

  SSLWiki wiki;
  @Override
  public SimpleNode moveTo(String nodeQuery, String ...keys) {
    return new MediaWikiPageNode(this,nodeQuery,keys);
  }

  @Override
  public SimpleSystem connect(String... connectionParams) throws Exception {
    if (connectionParams.length<2)
      throw new IllegalArgumentException("need url, scriptpath and optionally wikiid");
    String url=connectionParams[0];
    String scriptPath=connectionParams[1];
    switch (connectionParams.length) {
    case 2:
      wiki=new SSLWiki(url,scriptPath);
      break;
    case 3:
      String wikiId=connectionParams[2];
      wiki=new SSLWiki(url,scriptPath,wikiId);
      // wiki.setDebug(true);
      wiki.login();
    }
    SiteInfo siteinfo = wiki.getSiteInfo();
    //property("lang",siteinfo.getLang());
    this.setVersion(siteinfo.getVersion());
    this.setName(siteinfo.getGeneral().getSitename());
    return this;
  }

  @Override
  public Class<? extends SimpleNode> getNodeClass() {
    return MediaWikiPageNode.class;
  }

}
