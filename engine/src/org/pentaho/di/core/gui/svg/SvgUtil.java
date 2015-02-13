package org.pentaho.di.core.gui.svg;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.net.URL;

import org.apache.batik.bridge.BridgeContext;
import org.apache.batik.bridge.DocumentLoader;
import org.apache.batik.bridge.GVTBuilder;
import org.apache.batik.bridge.UserAgentAdapter;
import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.gvt.GraphicsNode;
import org.apache.xerces.jaxp.SAXParserImpl.JAXPSAXParser;
import org.w3c.dom.Document;

public class SvgUtil {

  /**
   * Load an SVG icon from a URL
   *
   * @param url locatin of the SVG icon
   *
   * @return a GraphicsNode which we can directly render onto a Graphics2D
   */
  public static GraphicsNode loadSvgIcon( URL url ) throws Exception {
    GraphicsNode svgGraphicsNode = null;
    try {
      SAXSVGDocumentFactory df = new SAXSVGDocumentFactory( JAXPSAXParser.class.getName() );

      // TODO: FIX SLOW SLOWNESS!!!
      // JAXP Apparently doesn't do non-validating, always loads referenced DTDs
      // So the next line doesn't do anything at all
      //
      df.setValidating( false );

      // Ant build causes error: incompatible types on next line:
      //
      Document document = df.createDocument( url.toString() );

      UserAgentAdapter userAgentAdapter = new UserAgentAdapter();
      DocumentLoader documentLoader = new DocumentLoader( userAgentAdapter );
      BridgeContext ctx = new BridgeContext( userAgentAdapter, documentLoader );
      GVTBuilder builder = new GVTBuilder();
      svgGraphicsNode = builder.build( ctx, document );
      return svgGraphicsNode;
    } catch ( Exception e ) {
      throw new Exception( "Unable to load SVG icon from file: " + url, e );
    }
  }

  /**
   * Render the SVG icon on the specified Graphics2D context. 
   * @param gc
   * @param svgGraphicsNode
   * @param positionX
   * @param postionY
   * @param scaleFactorX
   * @param scaleFactorY
   */
  public static void paintSvgIcon( Graphics2D gc, GraphicsNode svgGraphicsNode, int positionX, int postionY, double scaleFactorX, double scaleFactorY ) {
    AffineTransform affineTransform = new AffineTransform( scaleFactorX, 0.0, 0.0, scaleFactorY, positionX, postionY );
    svgGraphicsNode.setTransform( affineTransform );
    svgGraphicsNode.paint( gc );
  }
}
