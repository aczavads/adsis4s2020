package aula20201023.rest;

import java.util.Objects;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class CameraFotografica {
    @Id
    private String id;
    private String modelo;
    private int anoLancamento;

    @Enumerated(EnumType.STRING)
    private TipoImovel tipoSensor;

    public CameraFotografica() {
        id = UUID.randomUUID().toString();
    }

    public CameraFotografica(String modelo, int anoLancamento, TipoImovel tipoSensor) {
        this();
        this.modelo = modelo;
        this.anoLancamento = anoLancamento;
        this.tipoSensor = tipoSensor;
    }

    public CameraFotografica(String id, String modelo, int anoLancamento, TipoImovel tipoSensor) {
        this(modelo, anoLancamento, tipoSensor);
        this.id = id;
	}

	public String getId() {
        return id;
    }
    public String getModelo() {
        return modelo;
    }
    public int getAnoLancamento() {
        return anoLancamento;
    }
    public TipoImovel getTipoSensor() {
        return tipoSensor;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CameraFotografica)) {
            return false;
        }
        CameraFotografica cameraFotografica = (CameraFotografica) o;
        return Objects.equals(id, cameraFotografica.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    
}
