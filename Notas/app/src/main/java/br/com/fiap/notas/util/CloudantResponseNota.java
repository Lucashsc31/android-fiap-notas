package br.com.fiap.notas.util;

/**
 * Created by logonrm on 12/04/2017.
 */

public class CloudantResponseNota {

    private Integer totalRows;
    private Integer offset;
    private Row[] rows = null;

    public Integer getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Integer totalRows) {
        this.totalRows = totalRows;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Row[] getRows() {
        return rows;
    }

    public void setRows(Row[] rows) {
        this.rows = rows;
    }
}
