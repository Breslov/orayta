#ifndef MOBILEAPP_H
#define MOBILEAPP_H

#include <QDialog>
#include <QModelIndex>
#include <QTreeWidgetItem>
#include "booklist.h"


namespace Ui {
    class MobileApp;
}

class MobileApp : public QDialog
{
    Q_OBJECT

public:
    explicit MobileApp(QWidget *parent = 0);
    ~MobileApp();

protected:

private slots:

    void on_openBTN_clicked();

    void on_searchBTN_clicked();

    void on_bookmarksBTN_clicked();

    void on_aboutBTN_clicked();

    void on_treeWidget_clicked(const QModelIndex &index);

    void on_openMixed_clicked();

    void on_treeWidget_itemDoubleClicked(QTreeWidgetItem *item, int column);

    void on_toolButton_clicked();

private:
    Ui::MobileApp *ui;

    void showBook(Book *);

    BookList bookList;


};

#endif // MOBILEAPP_H
