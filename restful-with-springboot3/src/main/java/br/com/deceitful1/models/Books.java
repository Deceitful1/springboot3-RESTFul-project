package br.com.deceitful1.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Books implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String author;

    @Column(nullable = false, name = "launch_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime launchDate;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String title;

    public Books(Long id, String author, LocalDateTime launchDate, double price, String text)
    {
        this.id = id;
        this.author = author;
        this.launchDate = launchDate;
        this.price = price;
        this.title = title;
    }

    public Books()
    {
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public LocalDateTime getLaunchDate()
    {
        return launchDate;
    }

    public void setLaunchDate(LocalDateTime launchDate)
    {
        this.launchDate = launchDate;
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
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Books books)) return false;
        return Objects.equals(getId(), books.getId()) && Objects.equals(getAuthor(), books.getAuthor()) && Objects.equals(getLaunchDate(), books.getLaunchDate()) && Objects.equals(getPrice(), books.getPrice()) && Objects.equals(getTitle(), books.getTitle());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getId(), getAuthor(), getLaunchDate(), getPrice(), getTitle());
    }

    @Override
    public String toString()
    {
        return "Books{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", launchDate=" + launchDate +
                ", price=" + price +
                ", title='" + title + '\'' +
                '}';
    }
}
