package com.speed_liv.menu.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * Plat
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-10-14T09:37:49.653006400+01:00[Africa/Casablanca]", comments = "Generator version: 7.7.0")
public class Plat {

  private String id;

  private String nom;

  private Float prix;

  private Boolean disponible;

  public Plat id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   */
  
  @Schema(name = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Plat nom(String nom) {
    this.nom = nom;
    return this;
  }

  /**
   * Get nom
   * @return nom
   */
  
  @Schema(name = "nom", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("nom")
  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public Plat prix(Float prix) {
    this.prix = prix;
    return this;
  }

  /**
   * Get prix
   * @return prix
   */
  
  @Schema(name = "prix", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("prix")
  public Float getPrix() {
    return prix;
  }

  public void setPrix(Float prix) {
    this.prix = prix;
  }

  public Plat disponible(Boolean disponible) {
    this.disponible = disponible;
    return this;
  }

  /**
   * Get disponible
   * @return disponible
   */
  
  @Schema(name = "disponible", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("disponible")
  public Boolean getDisponible() {
    return disponible;
  }

  public void setDisponible(Boolean disponible) {
    this.disponible = disponible;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Plat plat = (Plat) o;
    return Objects.equals(this.id, plat.id) &&
        Objects.equals(this.nom, plat.nom) &&
        Objects.equals(this.prix, plat.prix) &&
        Objects.equals(this.disponible, plat.disponible);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nom, prix, disponible);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Plat {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    nom: ").append(toIndentedString(nom)).append("\n");
    sb.append("    prix: ").append(toIndentedString(prix)).append("\n");
    sb.append("    disponible: ").append(toIndentedString(disponible)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

