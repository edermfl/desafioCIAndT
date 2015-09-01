package br.com.ciandt.desafio.infra;

import com.thoughtworks.xstream.XStream;

public class XmlUtil {

	private final XStream xStream;

	public XmlUtil() {
		xStream  = new XStream();
	}
	
	public String gerarXmlApartirObjeto(Object pObject){
		return xStream.toXML(pObject);
	}
	
	public XmlUtil adicionarAlias(String pAlias, Class pClass){
		xStream.alias(pAlias, pClass);
		return this;
	}
	
	public Object gerarObjetoApatirXml(String pXml){
		Object objeto = xStream.fromXML(pXml);
		return objeto;
	}
	
	
}

