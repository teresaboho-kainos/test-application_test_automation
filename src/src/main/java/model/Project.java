package model;

public class Project {
    String name;
    String sector;
    String [] technology;
    String startDate;
    Integer owner;

    public Project(String name, String sector, String [] technology, String startDate, Integer owner) {
        this.name = name;
        this.sector = sector;
        this.technology = technology;
        this.startDate = startDate;
        this.owner = owner;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String [] getTechnology() {
        return technology;
    }

    public void setTechnology(String [] technology) {
        this.technology = technology;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Project{" +
                "name='" + name + '\'' +
                ", sector='" + sector + '\'' +
                ", technology='" + technology + '\'' +
                ", startDate='" + startDate + '\'' +
                ", owner=" + owner +
                '}';
    }
}