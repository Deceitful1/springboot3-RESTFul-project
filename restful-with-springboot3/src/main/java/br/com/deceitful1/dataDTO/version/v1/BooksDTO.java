package br.com.deceitful1.dataDTO.version.v1;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class BooksDTO extends RepresentationModel<BooksDTO> implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime launchDate;
    private String author;
    private Double price;
    private String title;

    public BooksDTO()
    {
    }

    public BooksDTO(Long id, LocalDateTime launchDate, String author, Double price, String title)
    {
        this.id = id;
        this.launchDate = launchDate;
        this.author = author;
        this.price = price;
        this.title = title;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public LocalDateTime getLaunchDate()
    {
        return launchDate;
    }

    public void setLaunchDate(LocalDateTime launchDate)
    {
        this.launchDate = launchDate;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public Double getPrice()
    {
        return price;
    }

    public void setPrice(Double price)
    {
        this.price = price;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    @Override
    public String toString()
    {
        return "BooksDTO{" +
                "id=" + id +
                ", launchDate=" + launchDate +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof BooksDTO booksDTO)) return false;
        return Objects.equals(getId(), booksDTO.getId()) && Objects.equals(getLaunchDate(), booksDTO.getLaunchDate()) && Objects.equals(getAuthor(), booksDTO.getAuthor()) && Objects.equals(getPrice(), booksDTO.getPrice()) && Objects.equals(getTitle(), booksDTO.getTitle());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getId(), getLaunchDate(), getAuthor(), getPrice(), getTitle());
    }
}
