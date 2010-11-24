/* ===========================================================
 * AFreeChart : a free chart library for Android(tm) platform.
 *              (based on JFreeChart and JCommon)
 * ===========================================================
 *
 * (C) Copyright 2010, by Icom Systech Co., Ltd.
 * (C) Copyright 2000-2008, by Object Refinery Limited and Contributors.
 *
 * Project Info:
 *    JFreeChart: http://www.jfree.org/jfreechart/index.html
 *    JCommon   : http://www.jfree.org/jcommon/index.html
 *    AFreeChart: http://code.google.com/p/afreechart/
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 * [Android is a trademark of Google Inc.]
 *
 * ----------------------
 * PieDatasetHandler.java
 * ----------------------
 * (C) Copyright 2010, by Icom Systech Co., Ltd.
 * (C) Copyright 2003-2008, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Sato Yoshiaki (for Icom Systech Co., Ltd);
 *                   Niwano Masayoshi;
 *
 * Changes
 * -------
 * 23-Jan-2003 : Version 1 (DG);
 *
 * ------------- AFREECHART 0.0.1 ---------------------------------------------
 * 19-Nov-2010 : port JFreeChart 1.0.13 to Android as "AFreeChart"
 */

package org.afree.data.xml;

import org.afree.data.general.DefaultPieDataset;
import org.afree.data.general.PieDataset;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * A SAX handler for reading a {@link PieDataset} from an XML file.
 */
public class PieDatasetHandler extends RootHandler implements DatasetTags {

    /** The pie dataset under construction. */
    private DefaultPieDataset dataset;

    /**
     * Default constructor.
     */
    public PieDatasetHandler() {
        this.dataset = null;
    }

    /**
     * Returns the dataset.
     *
     * @return The dataset.
     */
    public PieDataset getDataset() {
        return this.dataset;
    }

    /**
     * Adds an item to the dataset under construction.
     *
     * @param key  the key.
     * @param value  the value.
     */
    public void addItem(Comparable key, Number value) {
        this.dataset.setValue(key, value);
    }

    /**
     * Starts an element.
     *
     * @param namespaceURI  the namespace.
     * @param localName  the element name.
     * @param qName  the element name.
     * @param atts  the element attributes.
     *
     * @throws SAXException for errors.
     */
    public void startElement(String namespaceURI,
                             String localName,
                             String qName,
                             Attributes atts) throws SAXException {

        DefaultHandler current = getCurrentHandler();
        if (current != this) {
            current.startElement(namespaceURI, localName, qName, atts);
        }
        else if (localName.equals(PIEDATASET_TAG)) {
            this.dataset = new DefaultPieDataset();
        }
        else if (localName.equals(ITEM_TAG)) {
            ItemHandler subhandler = new ItemHandler(this, this);
            getSubHandlers().push(subhandler);
            subhandler.startElement(namespaceURI, localName, qName, atts);
        }

    }

    /**
     * The end of an element.
     *
     * @param namespaceURI  the namespace.
     * @param localName  the element name.
     * @param qName  the element name.
     *
     * @throws SAXException for errors.
     */
    public void endElement(String namespaceURI,
                           String localName,
                           String qName) throws SAXException {

        DefaultHandler current = getCurrentHandler();
        if (current != this) {
            current.endElement(namespaceURI, localName, qName);
        }

    }

}