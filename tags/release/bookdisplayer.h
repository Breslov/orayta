/* This program is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License version 2
* as published by the Free Software Foundation.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
*
* Author: Moshe Wagner. <moshe.wagner@gmail.com>
*/

#ifndef BOOKDISP_H
#define BOOKDISP_H

#include "mainwindow.h"
#include "mywebview.h"
#include "pdfwidget.h"
#include <QStackedWidget>

//TODO: This is all so stupid. It should be done with inheritance. Maybe some day.

/*
  Class holding all widgets and objects needed to display a book.
    The main object is a plain QWidget.

    In it, a stackedwidget allows us to switch between two htmlviewers,
     one for the "loading" image, and one for the book itself
*/

class myWebView;
class MainWindow;

class bookDisplayer : public QWidget
{
    //Very important. Without it, it won't create signalls or slots.
    Q_OBJECT

public:
    bookDisplayer(QWidget *parent, QTabWidget * tabviewptr);
    ~bookDisplayer();
    //Set the html that should be shown in the htmlviewer
    void setHtml(QString html);
    //Load given url
    void load(QUrl url);

    //The book displayed here
    void setBook( Book * );
    Book * book();

    bool isNikudShown();
    bool areTeamimShown();

    void showNikud(bool show);
    void showTeamim(bool show);

    int searchPos();
    void setSearchPos (int);

    QString title();
    //QUrl url();
    QString activeLink();

    virtual QVariant execScript(QString script);
    virtual QString getJSVar(QString var);

    void ZoomIn();
    void ZoomOut();
    void normalZoom();

    void setInternalLocation(QString location);
    void setSearchMarker(QRegExp marker);

    QWidget * htmlQWidget();
    myWebView * webview();
    PdfWidget * pdfview();
    
    QString pageText();
    QString htmlSource();

    void searchText(QString text, bool backwards);

    void ShowWaitPage();
    void HideWaitPage();

    void highlight(QRegExp);
    void unhighlight();

    #ifndef  POPPLER
        void enablePlugins();
        void disablePlugins();
    #else
        void setPdfPage(int page);
        void setPdfMode(bool mode);
    #endif
    bool getPdfMode();

    void jumpToTop();

    void keyPressEvent( QKeyEvent * event );

private:

    bool PDFMode;

    QString lastSearch;

    //Html viewer the book is shown within
    QPointer<myWebView> htmlview;
    QPointer<myWebView> waitView;

    #ifdef POPPLER
        QPointer<PdfWidget> pdfView;
    #endif

    MainWindow * MW;
    QTabWidget * TW;
    
    //Layout holding the widgets
    QVBoxLayout *vbox;

    //Wait movie
    QMovie *mov;

    //Label displaying the wait movie
    QLabel *waitlbl;

    //Location in html file next load should use
    QString InternalLocationInHtml;

    QRegExp SearchMarker;

    //Pointer to the book that's displayed here
    Book *myBook;

    bool shownikud;
    bool showteamim;

    QUrl myurl;
    QString mActiveLink;

    QStackedWidget *stackedWidget;

    //Position in search
    int mSearchPos;

private slots:
    void htmlView_linkClicked(QUrl url);

    void htmlView_loadFinished(bool);

signals:
    void externalLink(QString link);


};

#endif // BOOKDISP_H