/*
 * The MIT License
 *
 * Copyright 2015 benoit.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.apache.poi.xwpf.converter.core.styles.run;

import java.util.List;
import org.apache.poi.xwpf.converter.core.styles.Data;
import org.apache.poi.xwpf.converter.core.styles.XWPFStylesDocument;
import org.apache.poi.xwpf.usermodel.BodyElementType;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author benoit
 */
public class FontSizeDocDefaultsTestCase {

    @Test
    public void testWithNormalDocDefaultsRPR() throws Exception {
        internalTest(13f, "TestWithCustomDocDefaultsRPR.docx");
    }

    @Test
    public void testWithEmptyDocDefaultsRPR() throws Exception {
        internalTest(10f, "TestWithEmptyDocDefaultsRPR.docx");
    }

    @Test
    public void testWithoutDocDefaultsRPR() throws Exception {
        internalTest(11f, "TestWithoutDocDefaultsRPR.docx");
    }

    private void internalTest(Float size, String docName) throws Exception {

        XWPFDocument document = new XWPFDocument(Data.class.getResourceAsStream(docName));
        XWPFStylesDocument stylesDocument = new XWPFStylesDocument(document);
        List<IBodyElement> elements = document.getBodyElements();
        boolean ran = false;
        for (IBodyElement element : elements) {
            if (element.getElementType() == BodyElementType.PARAGRAPH) {
                for (XWPFRun docxRun : ((XWPFParagraph) element).getRuns()) {
                    Object sizeFromStyle = stylesDocument.getFontSize(docxRun);
                    ran = true;
                    assertEquals(sizeFromStyle, size);
                }
            }
        }
        assertTrue(ran);
    }
}
