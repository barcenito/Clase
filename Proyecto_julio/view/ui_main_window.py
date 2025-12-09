# -*- coding: utf-8 -*-

################################################################################
## Form generated from reading UI file 'main_window.ui'
##
## Created by: Qt User Interface Compiler version 6.10.1
##
## WARNING! All changes made in this file will be lost when recompiling UI file!
################################################################################

from PySide6.QtCore import (QCoreApplication, QDate, QDateTime, QLocale,
    QMetaObject, QObject, QPoint, QRect,
    QSize, QTime, QUrl, Qt)
from PySide6.QtGui import (QBrush, QColor, QConicalGradient, QCursor,
    QFont, QFontDatabase, QGradient, QIcon,
    QImage, QKeySequence, QLinearGradient, QPainter,
    QPalette, QPixmap, QRadialGradient, QTransform)
from PySide6.QtWidgets import (QApplication, QHBoxLayout, QHeaderView, QLabel,
    QListWidget, QListWidgetItem, QMainWindow, QPushButton,
    QSizePolicy, QTreeWidget, QTreeWidgetItem, QVBoxLayout,
    QWidget)

class Ui_MainWindow(object):
    def setupUi(self, MainWindow):
        if not MainWindow.objectName():
            MainWindow.setObjectName(u"MainWindow")
        MainWindow.resize(800, 600)
        self.centralwidget = QWidget(MainWindow)
        self.centralwidget.setObjectName(u"centralwidget")
        self.verticalLayout_Main = QVBoxLayout(self.centralwidget)
        self.verticalLayout_Main.setObjectName(u"verticalLayout_Main")
        self.horizontalLayout_Lists = QHBoxLayout()
        self.horizontalLayout_Lists.setObjectName(u"horizontalLayout_Lists")
        self.verticalLayout_Workers = QVBoxLayout()
        self.verticalLayout_Workers.setObjectName(u"verticalLayout_Workers")
        self.label = QLabel(self.centralwidget)
        self.label.setObjectName(u"label")

        self.verticalLayout_Workers.addWidget(self.label)

        self.tree_workers = QTreeWidget(self.centralwidget)
        self.tree_workers.setObjectName(u"tree_workers")
        self.tree_workers.setHeaderHidden(False)

        self.verticalLayout_Workers.addWidget(self.tree_workers)


        self.horizontalLayout_Lists.addLayout(self.verticalLayout_Workers)

        self.verticalLayout_Tasks = QVBoxLayout()
        self.verticalLayout_Tasks.setObjectName(u"verticalLayout_Tasks")
        self.label_2 = QLabel(self.centralwidget)
        self.label_2.setObjectName(u"label_2")

        self.verticalLayout_Tasks.addWidget(self.label_2)

        self.list_tasks = QListWidget(self.centralwidget)
        self.list_tasks.setObjectName(u"list_tasks")

        self.verticalLayout_Tasks.addWidget(self.list_tasks)


        self.horizontalLayout_Lists.addLayout(self.verticalLayout_Tasks)


        self.verticalLayout_Main.addLayout(self.horizontalLayout_Lists)

        self.horizontalLayout_Buttons = QHBoxLayout()
        self.horizontalLayout_Buttons.setObjectName(u"horizontalLayout_Buttons")
        self.horizontalLayout_WorkerActions = QHBoxLayout()
        self.horizontalLayout_WorkerActions.setObjectName(u"horizontalLayout_WorkerActions")
        self.btn_create_worker = QPushButton(self.centralwidget)
        self.btn_create_worker.setObjectName(u"btn_create_worker")

        self.horizontalLayout_WorkerActions.addWidget(self.btn_create_worker)

        self.btn_edit_worker = QPushButton(self.centralwidget)
        self.btn_edit_worker.setObjectName(u"btn_edit_worker")

        self.horizontalLayout_WorkerActions.addWidget(self.btn_edit_worker)


        self.horizontalLayout_Buttons.addLayout(self.horizontalLayout_WorkerActions)

        self.horizontalLayout_TaskActions = QHBoxLayout()
        self.horizontalLayout_TaskActions.setObjectName(u"horizontalLayout_TaskActions")
        self.btn_create_task = QPushButton(self.centralwidget)
        self.btn_create_task.setObjectName(u"btn_create_task")

        self.horizontalLayout_TaskActions.addWidget(self.btn_create_task)


        self.horizontalLayout_Buttons.addLayout(self.horizontalLayout_TaskActions)


        self.verticalLayout_Main.addLayout(self.horizontalLayout_Buttons)

        MainWindow.setCentralWidget(self.centralwidget)

        self.retranslateUi(MainWindow)

        QMetaObject.connectSlotsByName(MainWindow)
    # setupUi

    def retranslateUi(self, MainWindow):
        MainWindow.setWindowTitle(QCoreApplication.translate("MainWindow", u"Gestor de Tareas", None))
        self.label.setText(QCoreApplication.translate("MainWindow", u"Jerarqu\u00eda de Trabajadores", None))
        ___qtreewidgetitem = self.tree_workers.headerItem()
        ___qtreewidgetitem.setText(1, QCoreApplication.translate("MainWindow", u"Cargo", None));
        ___qtreewidgetitem.setText(0, QCoreApplication.translate("MainWindow", u"Nombre", None));
        self.label_2.setText(QCoreApplication.translate("MainWindow", u"Tareas", None))
        self.btn_create_worker.setText(QCoreApplication.translate("MainWindow", u"Crear Trabajador", None))
        self.btn_edit_worker.setText(QCoreApplication.translate("MainWindow", u"Editar", None))
        self.btn_create_task.setText(QCoreApplication.translate("MainWindow", u"Crear Tarea", None))
    # retranslateUi

