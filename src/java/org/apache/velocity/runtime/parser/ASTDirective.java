/* Generated By:JJTree: Do not edit this line. ASTDirective.java */

package org.apache.velocity.runtime.parser;

import java.io.Writer;
import java.io.IOException;

import org.apache.velocity.Context;
import org.apache.velocity.runtime.directive.Directive;

public class ASTDirective extends SimpleNode
{
    private Directive directive;
    private String directiveName;
    private boolean isDirective;
    
    public ASTDirective(int id)
    {
        super(id);
    }

    public ASTDirective(Parser p, int id)
    {
        super(p, id);
    }


    /** Accept the visitor. **/
    public Object jjtAccept(ParserVisitor visitor, Object data)
    {
        return visitor.visit(this, data);
    }
    
    public Object init(Context context, Object data) throws Exception
    {
        directiveName = getFirstToken().image.substring(1);
        
        if (parser.isDirective(directiveName))
        {
            isDirective = true;
            directive = parser.getDirective(directiveName);
            directive.init(context,this);
        }            
        else
        {
            isDirective = false;
        }            
    
        return data;
    }

    public void render(Context context, Writer writer)
        throws IOException
    {
        if (isDirective)
            directive.render(context, writer, this);
        else
            writer.write(directiveName);
    }
}
