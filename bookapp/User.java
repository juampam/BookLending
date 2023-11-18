package bookapp;

public class User {
    private String name;
    private String[] mybooks = new String[3];

    public User(){
        
    }
    
    /**
     * Get name.
     *
     * @return name as String.
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Set name.
     *
     * @param name the value to set.
     */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
     * Get mybooks.
     *
     * @return mybooks as String[].
     */
    public String[] getmybooks()
    {
        return mybooks;
    }
    
    /**
     * Get mybooks element at specified index.
     *
     * @param index the index.
     * @return mybooks at index as String.
     */
    public String getmybooks(int index)
    {
        return mybooks[index];
    }
    
    /**
     * Set mybooks.
     *
     * @param mybooks the value to set.
     */
    public void setmybooks(String[] mybooks)
    {
        this.mybooks = mybooks;
    }
    
    /**
     * Set mybooks at the specified index.
     *
     * @param mybooks the value to set.
     * @param index the index.
     */
    public void setmybooks(String mybooks, int index)
    {
        this.mybooks[index] = mybooks;
    }
}
