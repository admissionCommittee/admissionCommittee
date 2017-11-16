package com.github.admissionCommittee.web.jstlTags;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;


public class Button extends TagSupport {
    private static final Logger log = LoggerFactory.getLogger(Button.class);
    private String caption;
    private String url;

    public void setCaption(String caption){
        this.caption = caption;
    }
    public void setUrl(String url){
        this.url = url;
    }

    @Override
    public int doStartTag() throws JspException {
        StringBuilder html = new StringBuilder();
        html.append(String.format("<a href='%s'>",url));
        html.append(String.format("<div class='flatbutton'>%s</div></a>",caption));
        try {
            pageContext.getOut().print(html);
        }catch (IOException e){
            log.error("Error taglib out");
        }
        return SKIP_BODY;
    }
}
