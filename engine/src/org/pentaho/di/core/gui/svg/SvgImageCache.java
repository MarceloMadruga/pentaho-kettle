package org.pentaho.di.core.gui.svg;

import java.util.HashMap;
import java.util.Map;

import org.apache.batik.gvt.GraphicsNode;
import org.pentaho.di.trans.step.StepMeta;

public class SvgImageCache {
  private static SvgImageCache cache;

  private Map<StepMeta, Boolean> stepSvgFlag;
  private Map<StepMeta, GraphicsNode> stepNodeCache;

  private SvgImageCache() {
    stepNodeCache = new HashMap<StepMeta, GraphicsNode>();
    stepSvgFlag = new HashMap<StepMeta, Boolean>();

  }

  public static SvgImageCache getInstance() {
    if ( cache == null ) {
      cache = new SvgImageCache();
    }
    return cache;
  }

  public void store( StepMeta stepMeta, GraphicsNode svgNode ) {
    stepNodeCache.put( stepMeta, svgNode );
  }

  public GraphicsNode retrieve( StepMeta stepMeta ) {
    return stepNodeCache.get( stepMeta );
  }

  public void markSvg( StepMeta stepMeta, boolean flag ) {
    stepSvgFlag.put( stepMeta, new Boolean( flag ) );
  }

  public Boolean isSvg( StepMeta stepMeta ) {
    return stepSvgFlag.get( stepMeta );
  }

}
