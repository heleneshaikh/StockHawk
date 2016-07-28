package com.sam_chordas.android.stockhawk.data;

/**
 * Created by heleneshaikh on 27/07/16.
 */
public class Query {
    private Results results;

    private String count;

    private String created;

    private Diagnostics diagnostics;

    private String lang;

    public Results getResults ()
    {
        return results;
    }

    public void setResults (Results results)
    {
        this.results = results;
    }

    public String getCount ()
    {
        return count;
    }

    public void setCount (String count)
    {
        this.count = count;
    }

    public String getCreated ()
    {
        return created;
    }

    public void setCreated (String created)
    {
        this.created = created;
    }

    public Diagnostics getDiagnostics ()
    {
        return diagnostics;
    }

    public void setDiagnostics (Diagnostics diagnostics)
    {
        this.diagnostics = diagnostics;
    }

    public String getLang ()
    {
        return lang;
    }

    public void setLang (String lang)
    {
        this.lang = lang;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [results = "+results+", count = "+count+", created = "+created+", diagnostics = "+diagnostics+", lang = "+lang+"]";
    }
}
