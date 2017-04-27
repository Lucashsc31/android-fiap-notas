package br.com.fiap.notas.util;

import br.com.fiap.notas.entity.Doc;

/**
 * Created by logonrm on 12/04/2017.
 */

public class Row {

    private String id;
    private String key;
    private Value value;
    private Doc doc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public Doc getDoc() {
        return doc;
    }

    public void setDoc(Doc doc) {
        this.doc = doc;
    }

}