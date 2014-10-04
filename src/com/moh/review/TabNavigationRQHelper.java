package com.moh.review;

import com.moh.common.AbstractBean;
import com.moh.utils.Logger;

public class TabNavigationRQHelper extends AbstractBean {

    private boolean showTabSupervisor;
    private boolean showTabProgDirector;
    private boolean showTabPgmeDean;
    private boolean showTabCommittee;

    public TabNavigationRQHelper() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "TabNavigationRQHelper" );

        String roleID = ( String ) getFromSession( SESSION_ROLEID );

        setShowTabSupervisor( false );
        setShowTabProgDirector( false );
        setShowTabPgmeDean( false );
        setShowTabCommittee( false );

        if( roleID.equals( ROLE_SUPERVISOR ) ) {
            setShowTabSupervisor( true );
        }
        else {
            if( roleID.equals( ROLE_DIRECTOR ) ) {
                setShowTabSupervisor( true );
                setShowTabProgDirector( true );
            }
            else {
                if( roleID.equals( ROLE_DEAN ) ) {
                    setShowTabSupervisor( true );
                    setShowTabProgDirector( true );
                    setShowTabPgmeDean( true );
                }
                else {
                    if( roleID.equals( ROLE_COMMITTEE ) || roleID.equals( ROLE_ADMIN_COMMITTEE ) ) {
                        setShowTabSupervisor( true );
                        setShowTabProgDirector( true );
                        setShowTabPgmeDean( true );
                        setShowTabCommittee( true );
                    }
                }
            }
        }
    }

    public boolean getShowTabSupervisor() {
        return showTabSupervisor;
    }

    public void setShowTabSupervisor( boolean showTabSupervisor ) {
        this.showTabSupervisor = showTabSupervisor;
    }

    public boolean getShowTabProgDirector() {
        return showTabProgDirector;
    }

    public void setShowTabProgDirector( boolean showTabProgDirector ) {
        this.showTabProgDirector = showTabProgDirector;
    }

    public boolean getShowTabPgmeDean() {
        return showTabPgmeDean;
    }

    public void setShowTabPgmeDean( boolean showTabPgmeDean ) {
        this.showTabPgmeDean = showTabPgmeDean;
    }

    public boolean getShowTabCommittee() {
        return showTabCommittee;
    }

    public void setShowTabCommittee( boolean showTabCommittee ) {
        this.showTabCommittee = showTabCommittee;
    }
}
