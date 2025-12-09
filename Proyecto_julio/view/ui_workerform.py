# -*- coding: utf-8 -*-

################################################################################
## Form generated from reading UI file 'workerform.ui'
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
from PySide6.QtWidgets import (QAbstractButton, QApplication, QComboBox, QDialog,
    QDialogButtonBox, QFormLayout, QLabel, QLineEdit,
    QSizePolicy, QSpacerItem, QVBoxLayout, QWidget)

class Ui_WorkerForm(object):
    def setupUi(self, WorkerForm):
        if not WorkerForm.objectName():
            WorkerForm.setObjectName(u"WorkerForm")
        WorkerForm.resize(450, 350)
        WorkerForm.setModal(True)
        self.verticalLayout = QVBoxLayout(WorkerForm)
        self.verticalLayout.setObjectName(u"verticalLayout")
        self.formLayout = QFormLayout()
        self.formLayout.setObjectName(u"formLayout")
        self.label_nombre = QLabel(WorkerForm)
        self.label_nombre.setObjectName(u"label_nombre")

        self.formLayout.setWidget(0, QFormLayout.ItemRole.LabelRole, self.label_nombre)

        self.le_nombre = QLineEdit(WorkerForm)
        self.le_nombre.setObjectName(u"le_nombre")

        self.formLayout.setWidget(0, QFormLayout.ItemRole.FieldRole, self.le_nombre)

        self.label_apellido1 = QLabel(WorkerForm)
        self.label_apellido1.setObjectName(u"label_apellido1")

        self.formLayout.setWidget(1, QFormLayout.ItemRole.LabelRole, self.label_apellido1)

        self.le_apellido1 = QLineEdit(WorkerForm)
        self.le_apellido1.setObjectName(u"le_apellido1")

        self.formLayout.setWidget(1, QFormLayout.ItemRole.FieldRole, self.le_apellido1)

        self.label_apellido2 = QLabel(WorkerForm)
        self.label_apellido2.setObjectName(u"label_apellido2")

        self.formLayout.setWidget(2, QFormLayout.ItemRole.LabelRole, self.label_apellido2)

        self.le_apellido2 = QLineEdit(WorkerForm)
        self.le_apellido2.setObjectName(u"le_apellido2")

        self.formLayout.setWidget(2, QFormLayout.ItemRole.FieldRole, self.le_apellido2)

        self.label_rol = QLabel(WorkerForm)
        self.label_rol.setObjectName(u"label_rol")

        self.formLayout.setWidget(3, QFormLayout.ItemRole.LabelRole, self.label_rol)

        self.combo_rol = QComboBox(WorkerForm)
        self.combo_rol.setObjectName(u"combo_rol")

        self.formLayout.setWidget(3, QFormLayout.ItemRole.FieldRole, self.combo_rol)

        self.label_cargo = QLabel(WorkerForm)
        self.label_cargo.setObjectName(u"label_cargo")

        self.formLayout.setWidget(4, QFormLayout.ItemRole.LabelRole, self.label_cargo)

        self.le_cargo = QLineEdit(WorkerForm)
        self.le_cargo.setObjectName(u"le_cargo")

        self.formLayout.setWidget(4, QFormLayout.ItemRole.FieldRole, self.le_cargo)

        self.label_superior = QLabel(WorkerForm)
        self.label_superior.setObjectName(u"label_superior")

        self.formLayout.setWidget(5, QFormLayout.ItemRole.LabelRole, self.label_superior)

        self.combo_superior = QComboBox(WorkerForm)
        self.combo_superior.setObjectName(u"combo_superior")

        self.formLayout.setWidget(5, QFormLayout.ItemRole.FieldRole, self.combo_superior)


        self.verticalLayout.addLayout(self.formLayout)

        self.verticalSpacer = QSpacerItem(20, 40, QSizePolicy.Policy.Minimum, QSizePolicy.Policy.Expanding)

        self.verticalLayout.addItem(self.verticalSpacer)

        self.buttonBox = QDialogButtonBox(WorkerForm)
        self.buttonBox.setObjectName(u"buttonBox")
        self.buttonBox.setOrientation(Qt.Horizontal)
        self.buttonBox.setStandardButtons(QDialogButtonBox.Cancel|QDialogButtonBox.Ok)

        self.verticalLayout.addWidget(self.buttonBox)


        self.retranslateUi(WorkerForm)

        QMetaObject.connectSlotsByName(WorkerForm)
    # setupUi

    def retranslateUi(self, WorkerForm):
        WorkerForm.setWindowTitle(QCoreApplication.translate("WorkerForm", u"A\u00f1adir Nuevo Trabajador", None))
        self.label_nombre.setText(QCoreApplication.translate("WorkerForm", u"Nombre:", None))
        self.label_apellido1.setText(QCoreApplication.translate("WorkerForm", u"Primer Apellido:", None))
        self.label_apellido2.setText(QCoreApplication.translate("WorkerForm", u"Segundo Apellido:", None))
        self.label_rol.setText(QCoreApplication.translate("WorkerForm", u"Rol:", None))
        self.label_cargo.setText(QCoreApplication.translate("WorkerForm", u"Cargo Espec\u00edfico:", None))
        self.label_superior.setText(QCoreApplication.translate("WorkerForm", u"Superior Directo:", None))
    # retranslateUi

