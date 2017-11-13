package com.github.admissionCommittee.web.jstlTags;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class FormatLocalDate extends TagSupport {
    private static final Logger log = LoggerFactory.getLogger(FormatLocalDate.class);
    private LocalDate date;

    public void setDate(LocalDate date){
        this.date = date;
    }

    @Override
    public int doStartTag() throws JspException {
        Locale locale = pageContext.findAttribute("lang") == null ? new Locale("ru") : new Locale((String)pageContext.findAttribute("lang"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy", locale);

        try {
            pageContext.getOut().print(formatter.format(date));
        }catch (IOException e){
            log.error("Error tag out", e);
        }

        return SKIP_BODY;
    }
}
