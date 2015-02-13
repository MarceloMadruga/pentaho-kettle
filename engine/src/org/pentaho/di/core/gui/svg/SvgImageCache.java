package org.pentaho.di.core.gui.svg;

import java.util.HashMap;
import java.util.Map;

import org.apache.batik.gvt.GraphicsNode;

public class SvgImageCache {
  private static SvgImageCache cache;

  private Map<String, Boolean> stepSvgFlag;
  private Map<String, GraphicsNode> stepNodeCache;

  private SvgImageCache() {
    stepNodeCache = new HashMap<String, GraphicsNode>();
    stepSvgFlag = new HashMap<String, Boolean>();
  }

  public static SvgImageCache getInstance() {
    if ( cache == null ) {
      cache = new SvgImageCache();
    }
    return cache;
  }

  public void storeStepSvg( String pluginId, GraphicsNode svgNode ) {
    stepNodeCache.put( pluginId, svgNode );
  }

  public GraphicsNode retrieveStepSvg( String pluginId ) {
    return stepNodeCache.get( pluginId );
  }

  public void markStepSvg( String pluginId, boolean flag ) {
    stepSvgFlag.put( pluginId, new Boolean( flag ) );
  }

  public Boolean isStepSvg( String pluginId ) {
    return stepSvgFlag.get( pluginId );
  }

}
