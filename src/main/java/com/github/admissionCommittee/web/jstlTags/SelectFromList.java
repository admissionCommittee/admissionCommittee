package com.github.admissionCommittee.web.jstlTags;


import com.github.admissionCommittee.model.Faculty;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

public class SelectFromList extends TagSupport {
    private List<Faculty> list;

    public void setList(List<Faculty> list) {
        this.list = list;
    }

    @Override
    public int doStartTag() throws JspException {
        try {

            StringBuilder html = new StringBuilder();
            for (Faculty faculty : list) {

                html.append("<option value=\""+ faculty.getId() + "\">"+ faculty.getName() +"</option>");
            }
            pageContext.getOut().print(html);
        } catch (IOException e) {

        }
        return SKIP_BODY;
    }
}