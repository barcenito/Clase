# -*- coding: utf-8 -*-
# from odoo import http


# class AddonCarlos(http.Controller):
#     @http.route('/addon_carlos/addon_carlos', auth='public')
#     def index(self, **kw):
#         return "Hello, world"

#     @http.route('/addon_carlos/addon_carlos/objects', auth='public')
#     def list(self, **kw):
#         return http.request.render('addon_carlos.listing', {
#             'root': '/addon_carlos/addon_carlos',
#             'objects': http.request.env['addon_carlos.addon_carlos'].search([]),
#         })

#     @http.route('/addon_carlos/addon_carlos/objects/<model("addon_carlos.addon_carlos"):obj>', auth='public')
#     def object(self, obj, **kw):
#         return http.request.render('addon_carlos.object', {
#             'object': obj
#         })

