package org.pentaho.di.core.gui.svg;

import java.awt.geom.AffineTransform;

import org.apache.batik.bridge.BridgeContext;
import org.apache.batik.bridge.DocumentLoader;
import org.apache.batik.bridge.GVTBuilder;
import org.apache.batik.bridge.UserAgentAdapter;
import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.dom.svg.SVGOMDocument;
import org.apache.batik.gvt.GraphicsNode;
import org.apache.batik.util.XMLResourceDescriptor;

public class SvgUtil {
  /**
   * Method to fetch the SVG icon from a url
   *
   * @param url the url from which to fetch the SVG icon
   *
   * @return a graphics node object that can be used for painting
   */
  public static org.apache.batik.gvt.GraphicsNode getSvgIcon( java.net.URL url ) throws Exception {
    GraphicsNode svgIcon = null;
    try {
      String xmlParser = XMLResourceDescriptor.getXMLParserClassName();
      SAXSVGDocumentFactory df = new SAXSVGDocumentFactory( xmlParser );
      SVGOMDocument doc = df.createDocument( url.toString() );
      UserAgentAdapter userAgent = new UserAgentAdapter();
      DocumentLoader loader = new DocumentLoader( userAgent );
      BridgeContext ctx = new BridgeContext( userAgent, loader );
      GVTBuilder builder = new GVTBuilder();
      svgIcon = builder.build( ctx, doc );
    } catch ( Exception e ) {
      throw new Exception( "Unable to load SVG icon from file: " + url, e );
    }
    return svgIcon;
  }

  /**
   * Method to paint the icon using Graphics2D. Note that the scaling factors have nothing to do with the zoom
   * operation, the scaling factors set the size your icon relative to the other objects on your canvas.
   *
   * @param g the graphics context used for drawing
   *
   * @param svgIcon the graphics node object that contains the SVG icon information
   *
   * @param x the X coordinate of the top left corner of the icon
   *
   * @param y the Y coordinate of the top left corner of the icon
   *
   * @param scaleX the X scaling to be applied to the icon before drawing
   * 
   * @param scaleY the Y scaling to be applied to the icon before drawing
   */
  public static void paintSvgIcon( java.awt.Graphics2D g, org.apache.batik.gvt.GraphicsNode svgIcon, int x, int y, double scaleX, double scaleY ) {
    AffineTransform transform = new AffineTransform( scaleX, 0.0, 0.0, scaleY, x, y );
    svgIcon.setTransform( transform );
    svgIcon.paint( g );
  }
}
