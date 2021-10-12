package pl.sdacademy.main.pl.sdacademy.entity;

public class Member {

    private int id;
    private String name;
    private Integer start_number;
    private Integer run_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStart_number() {
        return start_number;
    }

    public void setStart_number(Integer start_number) {
        this.start_number = start_number;
    }

    public Integer getRun_id() {
        return run_id;
    }

    public void setRun_id(Integer run_id) {
        this.run_id = run_id;
    }
}
