package br.ufrj.mba.server.infraestructure.configuration;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
  /**
   * Registra o Servlet no container. Como o nome já diz efetua o registro do servlet no contexto da
   * aplicação.
   *
   * @param applicationContext Contexto da aplicação.
   */
  @Bean
  public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(
      ApplicationContext applicationContext) {
    MessageDispatcherServlet servlet = new MessageDispatcherServlet();
    servlet.setApplicationContext(applicationContext);
    servlet.setTransformWsdlLocations(true);
    return new ServletRegistrationBean<>(servlet, "/ws/*");
  }

  /**
   * Implementação que facilita a criação de serviços SOAP 1.1 e 1.2, fazendo o bind através de
   * convenções tendo como base arquivos xsd.
   *
   * @param publicationsSchema Esquema xsd que será utilizado para a implementação.
   */
  @Bean(name = "publications")
  public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema publicationsSchema) {
    DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
    wsdl11Definition.setPortTypeName("PublicationsPort");
    wsdl11Definition.setLocationUri("/ws");
    wsdl11Definition.setTargetNamespace("http://mba.ufrj.br/find-publication");
    wsdl11Definition.setSchema(publicationsSchema);
    return wsdl11Definition;
  }

  @Bean
  public XsdSchema publicationsSchema() {
    return new SimpleXsdSchema(new ClassPathResource("publications.xsd"));
  }
}
