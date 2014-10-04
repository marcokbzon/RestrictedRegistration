package com.moh.data.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.faces.model.SelectItem;

import com.moh.common.AbstractBean;
import com.moh.data.dao.ListAcDAO;

public class DocumentListBean extends AbstractBean {

    private List<SelectItem> documentList;
    private TreeMap<String, String> documents;
    private String email;

    public DocumentListBean() {
    }

    public List<SelectItem> getDocumentList() {
        if( documentList == null ) {
            documentList = new ArrayList<>();

            Iterator<Map.Entry<String, String>> documentIterator = ( Iterator<Map.Entry<String, String>> ) getDocuments().entrySet().iterator();

            while( documentIterator.hasNext() ) {
                Map.Entry<String, String> document = documentIterator.next();
                documentList.add( new SelectItem( document.getKey(), document.getValue() ) );
            }
        }

        return documentList;
    }

    public void setDocumentList( List<SelectItem> documentList ) {
        this.documentList = documentList;
    }

    public Map<String, String> getDocuments() {
        if( documents == null ) {
            documents = new TreeMap<>();

            setEmail( ( String ) getFromSession( SESSION_EMAIL ) );

            ListAcDAO listsDAO = new ListAcDAO();
            documents.putAll( listsDAO.getDocuments( getEmail() ) );
        }

        return documents;
    }

    public void setDocuments( TreeMap<String, String> documents ) {
        this.documents = documents;
    }
    
    private String getEmail() {
        if ( email == null || email.trim().equals( EMPTY_STRING ) ) {
            return EMPTY_STRING;
        }
        else {
            return email.trim().toLowerCase();
        }
    }
    
    private void setEmail( String email ) {
        if ( email == null || email.trim().equals( EMPTY_STRING ) ) {
            this.email = EMPTY_STRING;
        }
        else {
            this.email = email.trim().toLowerCase();
        }
    }
}
