package com.moh.data.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.faces.model.SelectItem;

import com.moh.common.AbstractBean;
import com.moh.common.GeneralSettings;

public class YearOfBirthBean extends AbstractBean {

    private int minimumYear;
    private int maximumYear;
    private List<SelectItem> yearList;
    private SortedMap<String, String> years;

    public YearOfBirthBean() {
    }

    /*
    public int getMinimumYear() {
        return minimumYear;
    }

    public void setMinimumYear( int minimumYear ) {
        this.minimumYear = minimumYear;
    }

    public int getMaximumYear() {
        return maximumYear;
    }

    public void setMaximumYear( int maximumYear ) {
        this.maximumYear = maximumYear;
    }
    */

    public List<SelectItem> getYearList() {
        if( yearList == null ) {
            yearList = new ArrayList<>();

            Iterator<Map.Entry<String, String>> yearIterator = ( Iterator<Map.Entry<String, String>> ) getYears().entrySet().iterator();

            while( yearIterator.hasNext() ) {
                Map.Entry<String, String> year = yearIterator.next();
                yearList.add( new SelectItem( year.getKey(), year.getValue() ) );
            }
        }

        return yearList;
    }

    public void setYearList( List<SelectItem> yearList ) {
        this.yearList = yearList;
    }

    public Map<String, String> getYears() {
        if( years == null ) {
            years = new TreeMap<>();
            
            //PAIRO
            Object yearOfBirthMin = getFromSession( SESSION_YEAROFBIRTH_MIN );
            Object yearOfBirthMax = getFromSession( SESSION_YEAROFBIRTH_MAX );

            if ( yearOfBirthMin == null || yearOfBirthMax == null || yearOfBirthMin.toString().trim().equals(EMPTY_STRING) || yearOfBirthMax.toString().trim().equals(EMPTY_STRING)) {
                GeneralSettings genSettings = new GeneralSettings();
                genSettings.initialize();
            }
            
            minimumYear = Integer.parseInt( getFromSession( SESSION_YEAROFBIRTH_MIN ).toString().trim() );
            maximumYear = Integer.parseInt( getFromSession( SESSION_YEAROFBIRTH_MAX ).toString().trim() );

            for( int i = maximumYear ; i >= minimumYear ; i-- ) {
                years.put( EMPTY_STRING + i, EMPTY_STRING + i );
            }
        }

        return years;
    }

    public void setYears( SortedMap<String, String> years ) {
        this.years = years;
    }
}
