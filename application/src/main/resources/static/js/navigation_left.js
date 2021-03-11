var basePath = window.location.protocol + "//" + window.location.host;
var user;
console.log(basePath);
$(document).ready(function (){
    $.ajax({
        url:"/getUserInfo",
        cache:false,
        async:false,
        type:"POST",
        success:function (result){
            if(result.retCode=="000000"){
                $("#user").append(result.item.cusName);
                $("#user").parent().css("background-color",result.item.color);
                user=result.item;
            }
        }
    });
});

$("#chanping").click(function () {
    var container="<div id=\"produceModel\">\n" +
        "        <div class=\"cdk-overlay-backdrop thy-popover-backdrop cdk-overlay-backdrop-showing\" onclick=\"clean('cdk-overlay-container','class')\"></div>\n" +
        "        <div class=\"cdk-overlay-connected-position-bounding-box\" dir=\"ltr\"\n" +
        "             style=\"top: 0px; left: 60px; height: 341.875px; width: 1220px; align-items: flex-start; justify-content: center;\">\n" +
        "            <div id=\"cdk-overlay-11\" class=\"cdk-overlay-pane portal nav-popover-container thy-popover-right\"\n" +
        "                 style=\"pointer-events: auto; position: static;\">\n" +
        "                <thy-popover-container\n" +
        "                        class=\"thy-popover-container ng-tns-c4-29 ng-trigger ng-trigger-popoverContainer ng-star-inserted\"\n" +
        "                        tabindex=\"-1\" role=\"popover\" style=\"transform: none;\"><!---->\n" +
        "                    <app-product-menu class=\"ng-star-inserted\">\n" +
        "                        <div class=\"main-apps\"><h3 class=\"apps-header-title\">产品</h3>\n" +
        "                            <ul class=\"apps clearfix\"><!---->\n" +
        "                                <li class=\"ng-star-inserted\" style=\"\"><a href=\"/agile\"><img class=\"title-icon\"\n" +
        "                                                                                                  src=\"./css/app-agile-square-fill-large.svg\">\n" +
        "                                    <div class=\"title-name\">\n" +
        "                                        <div class=\"name-en\"> Agile <!----></div>\n" +
        "                                        <div class=\"name-zh\">敏捷开发</div>\n" +
        "                                    </div>\n" +
        "                                </a></li>\n" +
        "                                <li class=\"ng-star-inserted\" style=\"\"><a href=\"javascript:;\"><img class=\"title-icon\"\n" +
        "                                                                                                  src=\"./css/app-testhub-square-fill-large.svg\">\n" +
        "                                    <div class=\"title-name\">\n" +
        "                                        <div class=\"name-en\"> Testhub <!----></div>\n" +
        "                                        <div class=\"name-zh\">测试管理</div>\n" +
        "                                    </div>\n" +
        "                                </a></li>\n" +
        "                                <li class=\"ng-star-inserted\" style=\"\"><a href=\"javascript:;\"><img class=\"title-icon\"\n" +
        "                                                                                                  src=\"./css/app-wiki-square-fill-large.svg\">\n" +
        "                                    <div class=\"title-name\">\n" +
        "                                        <div class=\"name-en\"> Wiki <!----></div>\n" +
        "                                        <div class=\"name-zh\">知识库</div>\n" +
        "                                    </div>\n" +
        "                                </a></li>\n" +
        "                                <li class=\"ng-star-inserted\" style=\"\"><a href=\"javascript:;\"><img class=\"title-icon\"\n" +
        "                                                                                                  src=\"./css/app-plan-square-fill-large.svg\">\n" +
        "                                    <div class=\"title-name\">\n" +
        "                                        <div class=\"name-en\"> Plan <!----></div>\n" +
        "                                        <div class=\"name-zh\">项目集</div>\n" +
        "                                    </div>\n" +
        "                                </a></li>\n" +
        "                            </ul>\n" +
        "                            <div class=\"apps-other\"><!----><a class=\"other-item ng-star-inserted\" href=\"/products\"\n" +
        "                                                              style=\"\">\n" +
        "                                <thy-icon class=\"mr-2 mt-1 thy-icon-products thy-icon\" thyiconname=\"products\">\n" +
        "                                    <svg viewBox=\"0 0 16 16\" xmlns=\"http://www.w3.org/2000/svg\" fit=\"\" height=\"1em\"\n" +
        "                                         width=\"1em\" preserveAspectRatio=\"xMidYMid meet\" focusable=\"false\">\n" +
        "                                        <g id=\"neapplication/products\" stroke-width=\"1\" fill-rule=\"evenodd\">\n" +
        "                                            <path d=\"M3.715 8.56h3.713v3.71a3.71 3.71 0 0 1-1.831 3.288 3.717 3.717 0 0 1-3.765 0 3.71 3.71 0 0 1-1.83-3.288 3.708 3.708 0 0 1 3.487-3.703l.226-.007zm8.57 0c1.503 0 2.857.904 3.432 2.292a3.708 3.708 0 0 1-3.217 5.124l-.206.006a3.712 3.712 0 0 1-3.717-3.508l-.006-.204V8.56h3.715zm-6.058 1.2H3.715c-.667 0-1.306.265-1.778.736a2.527 2.527 0 0 0-.736 1.803 2.51 2.51 0 0 0 1.239 2.224 2.516 2.516 0 0 0 3.786-2.066l.002-.187-.001-2.51zm6.059 0H9.77v2.51a2.51 2.51 0 0 0 1.552 2.32c.94.39 2.021.175 2.74-.544a2.508 2.508 0 0 0 .546-2.735 2.514 2.514 0 0 0-2.145-1.544l-.178-.007zM3.716 0l.197.005a3.713 3.713 0 0 1 3.51 3.508l.005.197v3.71H3.716A3.712 3.712 0 0 1 .09 3.71 3.712 3.712 0 0 1 3.522.01L3.716 0zm8.568.062a3.712 3.712 0 0 1 3.714 3.648 3.711 3.711 0 0 1-3.516 3.704l-.198.006H8.57V3.71A3.712 3.712 0 0 1 12.09.067l.194-.005zM3.725 1.2l-.142.009a2.513 2.513 0 0 0-2.287 2.338l-.005.163a2.51 2.51 0 0 0 2.266 2.5l.159.01 2.512-.001V3.71c0-1.28-.963-2.344-2.186-2.491l-.161-.014-.156-.005zm8.559.062A2.511 2.511 0 0 0 9.778 3.55l-.008.16v2.51h2.514a2.51 2.51 0 0 0 2.509-2.326l.005-.163a2.512 2.512 0 0 0-2.514-2.468z\"\n" +
        "                                                  id=\"ne形状\"></path>\n" +
        "                                        </g>\n" +
        "                                    </svg>\n" +
        "                                </thy-icon>\n" +
        "                                全部产品 </a><a class=\"other-item text-right\" href=\"/admin\">\n" +
        "                                <thy-icon class=\"mr-2 mt-1 thy-icon-settings thy-icon\" thyiconname=\"settings\">\n" +
        "                                    <svg viewBox=\"0 0 16 16\" xmlns=\"http://www.w3.org/2000/svg\" fit=\"\" height=\"1em\"\n" +
        "                                         width=\"1em\" preserveAspectRatio=\"xMidYMid meet\" focusable=\"false\">\n" +
        "                                        <g id=\"onaction/settings\" stroke-width=\"1\" fill-rule=\"evenodd\">\n" +
        "                                            <path d=\"M11.405 13.975l3.398-5.889L11.405 2.2H4.607L1.208 8.087l3.399 5.889h6.798zm1.023-12.4l3.43 5.938c.205.356.205.793 0 1.149l-3.43 5.938a1.147 1.147 0 0 1-.993.574H4.577c-.41 0-.789-.218-.994-.573L.153 8.66a1.153 1.153 0 0 1 0-1.147l3.43-5.94c.205-.356.584-.575.994-.575h6.858c.409 0 .788.22.993.576zM8.006 9.879c.988 0 1.792-.804 1.792-1.792s-.804-1.792-1.792-1.792-1.792.804-1.792 1.792.804 1.792 1.792 1.792zm0-4.784a2.993 2.993 0 1 1-.002 5.985 2.993 2.993 0 0 1 .002-5.985z\"\n" +
        "                                                  id=\"on形状结合\"></path>\n" +
        "                                        </g>\n" +
        "                                    </svg>\n" +
        "                                </thy-icon>\n" +
        "                                后台管理 </a></div>\n" +
        "                        </div>\n" +
        "                    </app-product-menu>\n" +
        "                </thy-popover-container>\n" +
        "            </div>\n" +
        "        </div>\n" +
        "    </div>";
    if($(".cdk-overlay-container").children().length==0){
        $(".cdk-overlay-container").html(container);
    }
    else {
        clean("cdk-overlay-container","class");
    }


});
function clean ( attributes,type) {
    var father;
    if(type==="class"){
        father=document.getElementsByClassName(attributes)[0];
    }else if(type==="id"){
        father=document.getElementById(attributes);
    }
    var children=father.childNodes;
    for(var var1=children.length-1;var1>=0;var1--){
        father.removeChild(children[var1]);
    }
}
$("#new").click(function (){
    var create="    <div class=\"cdk-overlay-backdrop thy-popover-backdrop cdk-overlay-backdrop-showing\" onclick=\"clean('cdk-overlay-container','class')\"></div>\n" +
        "    <div class=\"cdk-overlay-connected-position-bounding-box\" dir=\"ltr\" style=\"top: 200.972px; left: 60px; height: 327.028px; width: 2073px; align-items: flex-start; justify-content: flex-start;\">\n" +
        "        <div id=\"cdk-overlay-4\" class=\"cdk-overlay-pane portal nav-popover-container thy-popover-rightTop\" style=\"pointer-events: auto; position: static;\">\n" +
        "            <thy-popover-container class=\"thy-popover-container ng-tns-c4-6 ng-trigger ng-trigger-popoverContainer ng-star-inserted\"\n" +
        "                                   tabindex=\"-1\" role=\"popover\" style=\"transform: none;\">\n" +
        "                <!---->\n" +
        "                <app-add-menu class=\"ng-star-inserted add-menu\">\n" +
        "                    <thy-action-menu class=\"pb-0 action-menu ng-star-inserted\" style=\"\">\n" +
        "                        <a class=\"add-menu-item action-menu-item ng-star-inserted\" href=\"javascript:;\" thyactionmenuitem=\"\" style=\"\">\n" +
        "                            <styx-broad-object-displayer class=\"d-flex icon-text icon-text-sm\">\n" +
        "                                <thy-icon class=\"thy-icon-epic-square-fill thy-icon\" style=\"color: rgb(255, 135, 123);\"><svg viewBox=\"0 0 16 16\"\n" +
        "                                                                                                                             xmlns=\"http://www.w3.org/2000/svg\" fit=\"\" height=\"1em\" width=\"1em\" preserveAspectRatio=\"xMidYMid meet\"\n" +
        "                                                                                                                             focusable=\"false\">\n" +
        "                                    <g id=\"gyrd/epic-square-fill\" stroke-width=\"1\" fill-rule=\"evenodd\">\n" +
        "                                        <path d=\"M11.976 7.327l-4.61 6.114a.181.181 0 0 1-.132.057c-.025 0-.044-.007-.056-.02-.088-.024-.119-.074-.094-.15l.583-4.44H4.128c-.063 0-.107-.026-.13-.076-.039-.05-.033-.106.018-.17L8.927 2.53c.05-.076.106-.088.17-.038.061.013.093.063.093.15l-.583 4.44h3.238c.062 0 .106.027.131.077a.37.37 0 0 1 0 .169M12.767 0H3.191a3.201 3.201 0 0 0-3.19 3.192v9.575a3.201 3.201 0 0 0 3.19 3.192h9.576a3.201 3.201 0 0 0 3.191-3.192V3.192A3.201 3.201 0 0 0 12.768 0\"\n" +
        "                                              id=\"gyFill-1\"></path>\n" +
        "                                    </g>\n" +
        "                                </svg></thy-icon>\n" +
        "                                <span class=\"ng-star-inserted\">史诗</span>\n" +
        "                            </styx-broad-object-displayer>\n" +
        "                        </a>\n" +
        "                        <a class=\"add-menu-item action-menu-item ng-star-inserted\" href=\"javascript:;\" thyactionmenuitem=\"\">\n" +
        "                            <styx-broad-object-displayer class=\"d-flex icon-text icon-text-sm\">\n" +
        "                                <thy-icon class=\"thy-icon-user-story-square-fill thy-icon\" style=\"color: rgb(95, 187, 255);\"><svg viewBox=\"0 0 16 16\"\n" +
        "                                                                                                                                  xmlns=\"http://www.w3.org/2000/svg\" fit=\"\" height=\"1em\" width=\"1em\" preserveAspectRatio=\"xMidYMid meet\"\n" +
        "                                                                                                                                  focusable=\"false\">\n" +
        "                                    <g id=\"tcrd/user-story-square-fill\" stroke-width=\"1\" fill-rule=\"evenodd\">\n" +
        "                                        <path d=\"M4.645 4.64a.95.95 0 0 1 .949-.954h4.77c.528 0 .955.427.955.954v7.633L7.959 9.25l-3.32 3.023.006-7.633zm11.314 8.127V3.192A3.202 3.202 0 0 0 12.767 0H3.192A3.202 3.202 0 0 0 0 3.192v9.575a3.2 3.2 0 0 0 3.192 3.19h9.575a3.2 3.2 0 0 0 3.192-3.19z\"\n" +
        "                                              id=\"tcFill-1\"></path>\n" +
        "                                    </g>\n" +
        "                                </svg></thy-icon>\n" +
        "                                <span class=\"ng-star-inserted\">用户故事</span>\n" +
        "                            </styx-broad-object-displayer>\n" +
        "                        </a>\n" +
        "                        <a class=\"add-menu-item action-menu-item ng-star-inserted\" href=\"javascript:;\" thyactionmenuitem=\"\">\n" +
        "                            <styx-broad-object-displayer class=\"d-flex icon-text icon-text-sm\">\n" +
        "                                <thy-icon class=\"thy-icon-bug-square-fill thy-icon\" style=\"color: rgb(255, 117, 117);\"><svg viewBox=\"0 0 16 16\"\n" +
        "                                                                                                                            xmlns=\"http://www.w3.org/2000/svg\" fit=\"\" height=\"1em\" width=\"1em\" preserveAspectRatio=\"xMidYMid meet\"\n" +
        "                                                                                                                            focusable=\"false\">\n" +
        "                                    <g id=\"ebrd/bug-square-fill\" stroke-width=\"1\" fill-rule=\"evenodd\">\n" +
        "                                        <path d=\"M12.766 0a3.2 3.2 0 0 1 3.192 3.192v9.575a3.2 3.2 0 0 1-3.192 3.191H3.191A3.201 3.201 0 0 1 0 12.767V3.192A3.202 3.202 0 0 1 3.191 0h9.575zm-1.441 13.017c1.248-1.554 1.514-2.927.9-4.194-.607-1.25-1.73-1.719-3.562-1.365l2.662 5.559zM7.98 7.932L6.104 11.85a3.3 3.3 0 0 0 2.114.763c.647 0 1.25-.185 1.761-.507L7.98 7.932zm0-1.162c.761-.19 1.446-.269 2.053-.235.008-.07.011-.148.011-.231 0-.674-.255-1.28-.66-1.692a2.62 2.62 0 0 1 1.429-.415.547.547 0 0 0 0-1.094c-.98 0-1.825.35-2.517.958a1.7 1.7 0 0 0-.486.014 3.728 3.728 0 0 0-2.533-.972.547.547 0 0 0 0 1.094c.555 0 1.057.161 1.496.459a2.426 2.426 0 0 0-.607 1.872c.546-.004 1.151.076 1.814.242zm-3.345 6.247l2.662-5.56c-1.832-.353-2.955.116-3.561 1.366-.615 1.267-.35 2.64.9 4.194z\"\n" +
        "                                              id=\"eb形状结合\"></path>\n" +
        "                                    </g>\n" +
        "                                </svg></thy-icon>\n" +
        "                                <span class=\"ng-star-inserted\">缺陷</span>\n" +
        "                            </styx-broad-object-displayer>\n" +
        "                        </a>\n" +
        "                        </span>" +
        "                        <a class=\"add-menu-item action-menu-item ng-star-inserted\" href=\"javascript:;\" thyactionmenuitem=\"\">\n" +
        "                            <styx-broad-object-displayer class=\"d-flex icon-text icon-text-sm\">\n" +
        "                                <thy-icon class=\"thy-icon-feature-square-fill thy-icon\" style=\"color: rgb(145, 145, 249);\"><svg viewBox=\"0 0 16 16\"\n" +
        "                                                                                                                                xmlns=\"http://www.w3.org/2000/svg\" fit=\"\" height=\"1em\" width=\"1em\" preserveAspectRatio=\"xMidYMid meet\"\n" +
        "                                                                                                                                focusable=\"false\">\n" +
        "                                    <g id=\"hmrd/feature-square-fill\" stroke-width=\"1\" fill-rule=\"evenodd\">\n" +
        "                                        <path d=\"M13.285 10.633a.279.279 0 0 1-.326.278c-1.678-.28-3.254-.194-4.462.562a.153.153 0 0 1-.232-.132l.002-5.56a.56.56 0 0 1 .232-.463c1.173-.888 2.786-1.004 4.536-.708a.3.3 0 0 1 .252.297l-.002 5.726zm-5.824.84c-1.209-.756-2.782-.841-4.463-.563a.278.278 0 0 1-.325-.277l-.002-5.725a.3.3 0 0 1 .251-.299c1.751-.295 3.363-.18 4.537.708a.56.56 0 0 1 .23.463l.002 5.561a.151.151 0 0 1-.23.132zM12.766 0H3.192A3.2 3.2 0 0 0 0 3.193v9.575a3.2 3.2 0 0 0 3.192 3.192h9.574a3.2 3.2 0 0 0 3.192-3.192V3.192A3.2 3.2 0 0 0 12.766 0z\"\n" +
        "                                              id=\"hmFill-1\"></path>\n" +
        "                                    </g>\n" +
        "                                </svg></thy-icon>\n" +
        "                                <span class=\"ng-star-inserted\">特性</span>\n" +
        "                            </styx-broad-object-displayer>\n" +
        "                        </a>\n" +
        "                        <a class=\"add-menu-item action-menu-item ng-star-inserted\" href=\"javascript:;\" thyactionmenuitem=\"\">\n" +
        "                            <styx-broad-object-displayer class=\"d-flex icon-text icon-text-sm\">\n" +
        "                                <thy-icon class=\"thy-icon-test-case-square-fill thy-icon\" style=\"color: rgb(61, 220, 145);\"><svg viewBox=\"0 0 16 16\"\n" +
        "                                                                                                                                 xmlns=\"http://www.w3.org/2000/svg\" fit=\"\" height=\"1em\" width=\"1em\" preserveAspectRatio=\"xMidYMid meet\"\n" +
        "                                                                                                                                 focusable=\"false\">\n" +
        "                                    <g id=\"rkrd/test-case-square-fill\" stroke-width=\"1\" fill-rule=\"evenodd\">\n" +
        "                                        <path d=\"M12.767 0a3.202 3.202 0 0 1 3.192 3.192v9.575a3.201 3.201 0 0 1-3.192 3.191H3.192A3.2 3.2 0 0 1 0 12.767V3.192A3.202 3.202 0 0 1 3.192 0h9.575zm-6.66 4.633V5.97L4.956 8.092c.927-.21 1.974-.102 3.13.316 1.39.502 2.232.254 2.703-.758L9.867 5.97V4.633h.76V3.508H9.51a.442.442 0 0 0-.086-.008l-.662.008H5.379v1.125h.728zm5.234 4.026c-.722 1.13-1.961 1.407-3.584.82-1.479-.534-2.674-.482-3.625.133l-.466.86A1.377 1.377 0 0 0 4.879 12.5l6.242-.001a1.373 1.373 0 0 0 1.21-2.034l-.99-1.807z\"\n" +
        "                                              id=\"rk形状结合\"></path>\n" +
        "                                    </g>\n" +
        "                                </svg></thy-icon>\n" +
        "                                <span class=\"ng-star-inserted\">测试用例</span>\n" +
        "                            </styx-broad-object-displayer>\n" +
        "                        </a>\n" +
        "                        <a class=\"add-menu-item action-menu-item ng-star-inserted\" href=\"javascript:;\" thyactionmenuitem=\"\">\n" +
        "                            <styx-broad-object-displayer class=\"d-flex icon-text icon-text-sm\">\n" +
        "                                <thy-icon class=\"thy-icon-sprint-square-fill thy-icon\" style=\"color: rgb(25, 224, 219);\"><svg viewBox=\"0 0 16 16\"\n" +
        "                                                                                                                              xmlns=\"http://www.w3.org/2000/svg\" fit=\"\" height=\"1em\" width=\"1em\" preserveAspectRatio=\"xMidYMid meet\"\n" +
        "                                                                                                                              focusable=\"false\">\n" +
        "                                    <g id=\"pkrd/sprint-square-fill\" stroke-width=\"1\" fill-rule=\"evenodd\">\n" +
        "                                        <path d=\"M12.101 12.565v-.742h-9.9v-1.609h5.665a2.35 2.35 0 0 0 2.284-2.34 2.354 2.354 0 0 0-2.35-2.35 2.33 2.33 0 0 0-2.147 1.444h.714L4.773 8.954 3.178 6.968h.83C4.416 5.245 5.953 3.955 7.8 3.955a3.921 3.921 0 0 1 3.917 3.918c0 .88-.303 1.686-.797 2.341h1.181v-.838l1.984 1.594-1.984 1.595zM12.788 0H3.211A3.21 3.21 0 0 0 0 3.21v9.579A3.212 3.212 0 0 0 3.211 16h9.577A3.21 3.21 0 0 0 16 12.79V3.21A3.21 3.21 0 0 0 12.79 0z\"\n" +
        "                                              id=\"pkFill-1\"></path>\n" +
        "                                    </g>\n" +
        "                                </svg></thy-icon>\n" +
        "                                <span class=\"ng-star-inserted\">迭代</span>\n" +
        "                            </styx-broad-object-displayer>\n" +
        "                        </a>\n" +
        "                        <a class=\"add-menu-item action-menu-item ng-star-inserted\" href=\"javascript:;\" thyactionmenuitem=\"\">\n" +
        "                            <styx-broad-object-displayer class=\"d-flex icon-text icon-text-sm\">\n" +
        "                                <thy-icon class=\"thy-icon-workload-square-fill thy-icon\" style=\"color: rgb(255, 196, 66);\"><svg viewBox=\"0 0 16 16\"\n" +
        "                                                                                                                                xmlns=\"http://www.w3.org/2000/svg\" fit=\"\" height=\"1em\" width=\"1em\" preserveAspectRatio=\"xMidYMid meet\"\n" +
        "                                                                                                                                focusable=\"false\">\n" +
        "                                    <g id=\"tvrd/workload-square-fill\" stroke-width=\"1\" fill-rule=\"evenodd\">\n" +
        "                                        <path d=\"M12.8 0C14.56 0 16 1.44 16 3.2v9.6a3.21 3.21 0 0 1-3.2 3.199H3.2a3.21 3.21 0 0 1-3.2-3.2V3.2C0 1.44 1.44 0 3.2 0zM8 3a5 5 0 1 0 0 10A5 5 0 0 0 8 3zm0 1.4a.7.7 0 0 1 .7.7v2.2h2.2l.094.006A.7.7 0 0 1 10.9 8.7H8l-.095-.006A.7.7 0 0 1 7.3 8V5.1l.006-.094A.7.7 0 0 1 8 4.4z\"\n" +
        "                                              id=\"tv形状结合\"></path>\n" +
        "                                    </g>\n" +
        "                                </svg></thy-icon>\n" +
        "                                <span class=\"ng-star-inserted\">工时</span>\n" +
        "                            </styx-broad-object-displayer>\n" +
        "                        </a>\n" +
        "\n" +
        "\n" +
        "                    </thy-action-menu>\n" +
        "                    <!---->\n" +
        "                </app-add-menu>\n" +
        "            </thy-popover-container>\n" +
        "        </div>\n" +
        "    </div>\n";
    if($(".cdk-overlay-container").children().length==0){
        $(".cdk-overlay-container").html(create);
    }
    else {
        clean("cdk-overlay-container","class");
    }
});


