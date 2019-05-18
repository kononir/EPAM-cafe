package com.epam.cafe.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.math.BigDecimal;

public class MenuDishTag extends TagSupport {
    private String name;
    private String imageHref;
    private BigDecimal cost;

    public void setName(String name) {
        this.name = name;
    }

    public void setImageHref(String imageHref) {
        this.imageHref = imageHref;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            out.write("<div class=\"menu-dish\">");
            out.write("<h3>Name: " + name + "</h3>");

            if (imageHref != null) {
                out.write("<img " + "href=\"" + imageHref + "\" alt=\"" + name + "\">" + "</img>");
            }

            out.write("<p>Cost: " + cost.toString() + "</p>");
            out.write("<p>Description: ");
        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }

        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doAfterBody() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            out.write("</p>");
        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }

        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            out.write("</div>");
        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }

        return EVAL_PAGE;
    }
}
