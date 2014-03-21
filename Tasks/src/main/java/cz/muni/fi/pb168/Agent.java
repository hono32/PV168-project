package cz.muni.fi.pb168;

/**
 * Created by Jaromir on 8.3.14.
 */
public class Agent
{
    private int id;
    private String codename;
    private Specialization spec;

    public Agent (int id)
    {
        this.id = id;
    }

    public Agent (int id, String codename, Specialization spec)
    {
        this.id = id;
        this.codename = codename;
        this.spec = spec;
    }

    public int getId()
    {
        return  id;
    }

    public String getCodename()
    {
        return codename;
    }

    public Specialization getSpecialization()
    {
        return  spec;
    }

    @Override
    public String toString()
    {
        return id + " " + codename + " " +spec.toString();
    }
}
