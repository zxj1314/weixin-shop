package com.cbd.weixin.utils;

import com.cbd.weixin.domain.TextMessage;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 痞老板
 * @Project: zheng-wechat-mp-service
 * @Package:com.zheng.wechat.mp.service.util
 * @date 2018/1/16 10:55
 **/
public class MessageUtil {
    public static String PREFIX_CDATA = "<![CDATA[";
    public static String SUFFIX_CDATA = "]]>";
    private static XStream xstream = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点的转换都增加CDATA标记
                boolean cdata = true;
                @SuppressWarnings("unchecked")
                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }
                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write(PREFIX_CDATA);
                        writer.write(text);
                        writer.write(SUFFIX_CDATA);
                    } else {
                        super.writeText(writer, text);
                    }
                }
            };
        }
    });

   /**
    *@author 痞老板
    *@description xml转map工具
    *@param [request]
    *@return java.util.Map<java.lang.String,java.lang.String>
    *@date 2018/1/16 10:57
    */
    @SuppressWarnings("unchecked")
    public static Map<String, String> xmlToMap(HttpServletRequest request) throws IOException {
        Map<String, String> map = new HashMap<String, String>();
        SAXReader reader = new SAXReader();

        InputStream ins = null;
        try {
            ins = request.getInputStream();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Document doc = null;
        try {
            doc = reader.read(ins);
            Element root = doc.getRootElement();

            List<Element> list = root.elements();

            for (Element e : list) {
                map.put(e.getName(), e.getText());
            }

            return map;
        } catch (DocumentException e1) {
            e1.printStackTrace();
        }finally{
            ins.close();
        }

        return null;
    }
    /**
     *@author 痞老板
     *@description pojo转xml
     *@param [textMessage]
     *@return java.lang.String
     *@date 2018/1/16 11:33
     */
    public static String textMessageToXml(TextMessage textMessage){
        XStream xstream = new XStream();
        xstream.alias("xml", textMessage.getClass());
        return xstream.toXML(textMessage);
    }

}
