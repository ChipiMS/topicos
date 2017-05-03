package espotifai;
public class Genero {
    private String Name;
    private int GenreId;
    public Genero(String Name){
        this.Name = Name;
    }
    public Genero(int GenreId,String Name){
        this.Name = Name;
        this.GenreId = GenreId;
    }

    /**
     * @return the Name
     */
    public String getName() {
        return Name;
    }

    /**
     * @param Name the Name to set
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     * @return the GenreId
     */
    public int getGenreId() {
        return GenreId;
    }

    /**
     * @param GenreId the GenreId to set
     */
    public void setGenreId(int GenreId) {
        this.GenreId = GenreId;
    }

    @Override
    public String toString() {
        return Name;
    }
    
    
}
