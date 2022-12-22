package com.example.imovers.annonces.ImageData;


import com.example.imovers.annonces.Annonce;
import com.example.imovers.annonces.Visibility.Visibility;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ImageData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name, type;

    @Lob
    @Column(name = "imagedata")
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] imageData;

    @ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JsonBackReference
//    @JsonIgnore
    private Annonce annonce;
}
