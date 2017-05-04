package espotifai;
public class TipoMedio {
    private String Name;
    private int MediaTypeId;
    public TipoMedio(String Name){
        this.Name = Name;
    }
    public TipoMedio(int MediaTypeId,String Name){
        this.Name = Name;
        this.MediaTypeId = MediaTypeId;
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
     * @return the MediaTypeId
     */
    public int getMediaTypeId() {
        return MediaTypeId;
    }

    /**
     * @param MediaTypeId the MediaTypeId to set
     */
    public void setMediaTypeId(int MediaTypeId) {
        this.MediaTypeId = MediaTypeId;
    }

    @Override
    public String toString() {
        return Name;
    }
    
    
}
