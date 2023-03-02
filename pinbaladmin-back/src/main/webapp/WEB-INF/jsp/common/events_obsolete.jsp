<%@ page contentType="text/html; charset=UTF-8" language="java"%><%@ include
    file="/WEB-INF/jsp/moduls/includes.jsp"%>

<link rel="stylesheet" type="text/css" media="all"
    href="<c:url value="/ayuda/css/morfos/reset.css"/>">
<link rel="stylesheet" type="text/css" media="all"
    href="<c:url value="/ayuda/css/morfos/main.css"/>">
 <!--    
<link rel="stylesheet" type="text/css" media="all" href="<c:url value="/ayuda/css/morfos/typography.css"/>">
-->
<link rel="stylesheet" type="text/css" media="all"
    href="<c:url value="/ayuda/css/morfos/libraries.css"/>">
<link rel="stylesheet" type="text/css" media="all"
    href="<c:url value="/ayuda/css/morfos/components.css"/>">
<link rel="stylesheet" type="text/css" media="all"
    href="<c:url value="/ayuda/css/morfos/colors.css"/>">
<link rel="stylesheet" type="text/css" media="all"
    href="<c:url value="/ayuda/css/morfos/grid-min.css"/>">
<link rel="stylesheet" type="text/css" media="all"
    href="<c:url value="/ayuda/css/morfos/menus.css"/>">
<link rel="stylesheet" type="text/css" media="screen"
    href="<c:url value="/ayuda/css/frontend/custom.css"/>">
<link rel="stylesheet" type="text/css" media="screen"
    href="<c:url value="/ayuda/css/jquery/jquery-ui.css"/>">
<link rel="stylesheet" type="text/css" media="screen"
    href="<c:url value="/ayuda/css/timeline/timeline.css"/>">
    

 
<script type="text/javascript" src="<c:url value="/ayuda/js/jquery/jquery.js"/>"></script>
     
<script type="text/javascript" src="<c:url value="/ayuda/js/jquery/jquery-ui.js"/>"></script>

<script type="text/javascript" src="<c:url value="/ayuda/js/morfos/main.js"/>"></script>


<style>
.full-width {
 max-width: 1200px;
 margin: 0 auto;
 /*background-color:#CFCFC4;*/
 border-left: 1px solid #C0C6BD;
 border-right: 1px solid #C0C6BD;
 border-top: 1px solid #C0C6BD;
 border-bottom: 1px solid #C0C6BD;
 padding: 15px;
}

.timeline-content {
 height: auto !important;
}

.timeline-date {
 width: 250px !important;
 font-size: 10pt;
}

.label-custom {
 border-radius: 0.25em;
 color: #fff;
 display: inline;
 font-weight: 700;
 line-height: 1;
 padding: 0.2em 0.6em 0.3em;
 text-align: center;
 vertical-align: baseline;
 white-space: nowrap;
 background-color: #A3A3A3;
}


.minibutton {
  appearance: button;
 
 background-color: rgb(218, 79, 73);
background-image: linear-gradient(rgb(238, 95, 91), rgb(189, 54, 47));
 
  background-repeat: repeat-x;border-bottom-color: rgba(0, 0, 0, 0.25);border-bottom-left-radius: 3px;
  border-bottom-right-radius: 3px;border-bottom-style: solid;border-bottom-width: 1px;border-image-outset: 0;border-image-repeat: stretch;
  border-image-slice: 100%;border-image-source: none;border-image-width: 1;border-left-color: rgba(0, 0, 0, 0.1);border-left-style: solid;
  border-left-width: 1px;border-right-color: rgba(0, 0, 0, 0.1);border-right-style: solid;border-right-width: 1px;border-top-color: rgba(0, 0, 0, 0.1);
  border-top-left-radius: 3px;border-top-right-radius: 3px;border-top-style: solid;border-top-width: 1px;
  box-shadow: rgba(255, 255, 255, 0.2) 0px 1px 0px 0px inset, rgba(0, 0, 0, 0.05) 0px 1px 2px 0px;color: rgb(255, 255, 255);
  cursor: pointer;display: inline-block;font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;font-size: 10.5px;font-weight: 400;line-height: 20px;
  margin-bottom: 0px;margin-left: 0px;margin-right: 0px;margin-top: 0px;
  padding-bottom: 4px;padding-left: 4px;padding-right: 4px;padding-top: 4px;
  text-align: center;text-shadow: rgba(0, 0, 0, 0.25) 0px -1px 0px;vertical-align: middle;
}


.minibutton1 {
  appearance: button;

 background-color: rgb(0, 109, 204);background-image: linear-gradient(rgb(0, 136, 204), rgb(0, 68, 204)); 
  

  background-repeat: repeat-x;border-bottom-color: rgba(0, 0, 0, 0.25);border-bottom-left-radius: 3px;
  border-bottom-right-radius: 3px;border-bottom-style: solid;border-bottom-width: 1px;border-image-outset: 0;border-image-repeat: stretch;
  border-image-slice: 100%;border-image-source: none;border-image-width: 1;border-left-color: rgba(0, 0, 0, 0.1);border-left-style: solid;
  border-left-width: 1px;border-right-color: rgba(0, 0, 0, 0.1);border-right-style: solid;border-right-width: 1px;border-top-color: rgba(0, 0, 0, 0.1);
  border-top-left-radius: 3px;border-top-right-radius: 3px;border-top-style: solid;border-top-width: 1px;
  box-shadow: rgba(255, 255, 255, 0.2) 0px 1px 0px 0px inset, rgba(0, 0, 0, 0.05) 0px 1px 2px 0px;color: rgb(255, 255, 255);
  cursor: pointer;display: inline-block;font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;font-size: 10.5px;font-weight: 400;line-height: 20px;
  margin-bottom: 0px;margin-left: 0px;margin-right: 0px;margin-top: 0px;
  padding-bottom: 2px;padding-left: 2px;padding-right: 2px;padding-top: 2px;
  text-align: center;text-shadow: rgba(0, 0, 0, 0.25) 0px -1px 0px;vertical-align: middle;
}



.iconpencil {
background-image: url("<c:url value="/img/glyphicons-halflings-white.png"/>");
background-position: 0px -72px;
background-position-x: 0px;
background-position-y: -72px;
background-repeat: no-repeat;
box-sizing: border-box;
color: rgb(102, 102, 102);
cursor: pointer;
display: inline-block;
font-family: Arial, "Helvetica Neue", Helvetica, sans-serif;
font-size: 10.5px;
height: 14px;
line-height: 14px;
margin-top: -1px;
text-align: center;
text-shadow: rgba(255, 255, 255, 0.75) 0px 1px 1px;
vertical-align: text-top;
width: 14px;
}

<%--

.cercle {
align-content: normal;
align-items: normal;
align-self: auto;
animation-delay: 0s;
animation-direction: normal;
animation-duration: 0s;
animation-fill-mode: none;
animation-iteration-count: 1;
animation-name: none;
animation-play-state: running;
animation-timing-function: ease;
appearance: none;
aspect-ratio: auto;
backface-visibility: visible;
background-attachment: scroll;
background-blend-mode: normal;
background-clip: border-box;
background-color: rgb(255, 255, 255);
background-image: none;
background-origin: padding-box;
background-position: 0% 0%;
background-position-x: 0%;
background-position-y: 0%;
background-repeat: repeat;
background-size: auto;
block-size: 32px;
border-block-end-color: rgb(255, 0, 0);
border-block-end-style: solid;
border-block-end-width: 4px;
border-block-start-color: rgb(255, 0, 0);
border-block-start-style: solid;
border-block-start-width: 4px;
border-bottom-color: rgb(255, 0, 0);
border-bottom-left-radius: 100%;
border-bottom-right-radius: 100%;
border-bottom-style: solid;
border-bottom-width: 4px;
border-collapse: separate;
border-end-end-radius: 100%;
border-end-start-radius: 100%;
border-image-outset: 0;
border-image-repeat: stretch;
border-image-slice: 100%;
border-image-source: none;
border-image-width: 1;
border-inline-end-color: rgb(255, 0, 0);
border-inline-end-style: solid;
border-inline-end-width: 4px;
border-inline-start-color: rgb(255, 0, 0);
border-inline-start-style: solid;
border-inline-start-width: 4px;
border-left-color: rgb(255, 0, 0);
border-left-style: solid;
border-left-width: 4px;
border-right-color: rgb(255, 0, 0);
border-right-style: solid;
border-right-width: 4px;
border-spacing: 0px 0px;
border-start-end-radius: 100%;
border-start-start-radius: 100%;
border-top-color: rgb(255, 0, 0);
border-top-left-radius: 100%;
border-top-right-radius: 100%;
border-top-style: solid;
border-top-width: 4px;
bottom: 93.0167px;
box-decoration-break: slice;
box-shadow: none;
box-sizing: border-box;
break-after: auto;
break-before: auto;
break-inside: auto;
caption-side: top;
caret-color: rgb(89, 89, 89);
clear: none;
clip: auto;
clip-path: none;
clip-rule: nonzero;
color: rgb(89, 89, 89);
color-adjust: economy;
color-interpolation: srgb;
color-interpolation-filters: linearrgb;
column-count: auto;
column-fill: balance;
column-gap: normal;
column-rule-color: rgb(89, 89, 89);
column-rule-style: none;
column-rule-width: 0px;
column-span: none;
column-width: auto;
contain: none;
content: normal;
counter-increment: none;
counter-reset: none;
counter-set: none;
cursor: auto;
cx: 0px;
cy: 0px;
direction: ltr;
display: block;
dominant-baseline: auto;
empty-cells: show;
fill: rgb(0, 0, 0);
fill-opacity: 1;
fill-rule: nonzero;
filter: none;
flex-basis: auto;
flex-direction: row;
flex-grow: 0;
flex-shrink: 1;
flex-wrap: nowrap;
float: none;
flood-color: rgb(0, 0, 0);
flood-opacity: 1;
font-family: Arial, "Helvetica Neue", Helvetica, sans-serif;
font-feature-settings: normal;
font-kerning: auto;
font-language-override: normal;
font-optical-sizing: auto;
font-size: 12px;
font-size-adjust: none;
font-stretch: 100%;
font-style: normal;
font-synthesis: weight style;
font-variant: normal;
font-variant-alternates: normal;
font-variant-caps: normal;
font-variant-east-asian: normal;
font-variant-ligatures: normal;
font-variant-numeric: normal;
font-variant-position: normal;
font-variation-settings: normal;
font-weight: 400;
grid-auto-columns: auto;
grid-auto-flow: row;
grid-auto-rows: auto;
grid-column-end: auto;
grid-column-start: auto;
grid-row-end: auto;
grid-row-start: auto;
grid-template-areas: none;
grid-template-columns: none;
grid-template-rows: none;
height: 32px;
hyphens: manual;
image-orientation: from-image;
image-rendering: auto;
ime-mode: auto;
inline-size: 32px;
inset-block-end: 93.0167px;
inset-block-start: 40px;
inset-inline-end: 423px;
inset-inline-start: 439px;
isolation: auto;
justify-content: normal;
justify-items: normal;
justify-self: auto;
left: 439px;
letter-spacing: normal;
lighting-color: rgb(255, 255, 255);
line-break: auto;
line-height: normal;
list-style-image: none;
list-style-position: outside;
list-style-type: disc;
margin-block-end: 0px;
margin-block-start: -2px;
margin-bottom: 0px;
margin-inline-end: 0px;
/* margin-inline-start: -16px; */
/* margin-left: -16px; */
margin-right: 0px;
margin-top: -2px;
marker-end: none;
marker-mid: none;
marker-start: none;
mask: none;
mask-clip: border-box;
mask-composite: add;
mask-image: none;
mask-mode: match-source;
mask-origin: border-box;
mask-position: 0% 0%;
mask-position-x: 0%;
mask-position-y: 0%;
mask-repeat: repeat;
mask-size: auto;
mask-type: luminance;
max-block-size: none;
max-height: none;
max-inline-size: none;
max-width: none;
min-block-size: 0px;
min-height: 0px;
min-inline-size: 0px;
min-width: 0px;
mix-blend-mode: normal;
object-fit: fill;
object-position: 50% 50%;
offset-anchor: auto;
offset-distance: 0px;
offset-path: none;
offset-rotate: auto;
opacity: 1;
order: 0;
outline-color: rgb(89, 89, 89);
outline-offset: 0px;
outline-style: none;
outline-width: 0px;
overflow: visible;
overflow-anchor: auto;
overflow-block: visible;
overflow-inline: visible;
overflow-wrap: normal;
overflow-x: visible;
overflow-y: visible;
overscroll-behavior-block: auto;
overscroll-behavior-inline: auto;
overscroll-behavior-x: auto;
overscroll-behavior-y: auto;
padding-block-end: 0px;
padding-block-start: 0px;
padding-bottom: 0px;
padding-inline-end: 0px;
padding-inline-start: 0px;
padding-left: 0px;
padding-right: 0px;
padding-top: 0px;
page-break-after: auto;
page-break-before: auto;
paint-order: normal;
perspective: none;
perspective-origin: 16px 16px;
pointer-events: auto;
/* position: absolute; */
quotes: auto;
r: 0px;
resize: none;
right: 423px;
rotate: none;
row-gap: normal;
ruby-align: space-around;
ruby-position: alternate;
rx: auto;
ry: auto;
scale: none;
scroll-behavior: auto;
scroll-margin-block-end: 0px;
scroll-margin-block-start: 0px;
scroll-margin-bottom: 0px;
scroll-margin-inline-end: 0px;
scroll-margin-inline-start: 0px;
scroll-margin-left: 0px;
scroll-margin-right: 0px;
scroll-margin-top: 0px;
scroll-padding-block-end: auto;
scroll-padding-block-start: auto;
scroll-padding-bottom: auto;
scroll-padding-inline-end: auto;
scroll-padding-inline-start: auto;
scroll-padding-left: auto;
scroll-padding-right: auto;
scroll-padding-top: auto;
scroll-snap-align: none;
scroll-snap-type: none;
scrollbar-color: auto;
scrollbar-width: auto;
shape-image-threshold: 0;
shape-margin: 0px;
shape-outside: none;
shape-rendering: auto;
stop-color: rgb(0, 0, 0);
stop-opacity: 1;
stroke: none;
stroke-dasharray: none;
stroke-dashoffset: 0px;
stroke-linecap: butt;
stroke-linejoin: miter;
stroke-miterlimit: 4;
stroke-opacity: 1;
stroke-width: 1px;
table-layout: auto;
text-align: center;
text-align-last: auto;
text-anchor: start;
text-combine-upright: none;
text-decoration: rgb(89, 89, 89);
text-decoration-color: rgb(89, 89, 89);
text-decoration-line: none;
text-decoration-skip-ink: auto;
text-decoration-style: solid;
text-decoration-thickness: auto;
text-emphasis-color: rgb(89, 89, 89);
text-emphasis-position: over right;
text-emphasis-style: none;
text-indent: 0px;
text-justify: auto;
text-orientation: mixed;
text-overflow: clip;
text-rendering: auto;
text-shadow: none;
text-transform: none;
text-underline-offset: auto;
text-underline-position: auto;
top: 40px;
touch-action: auto;
transform: none;
transform-box: border-box;
transform-origin: 16px 16px;
transform-style: flat;
transition-delay: 0s;
transition-duration: 0s;
transition-property: all;
transition-timing-function: ease;
translate: none;
unicode-bidi: isolate;
user-select: auto;
vector-effect: none;
vertical-align: baseline;
visibility: visible;
white-space: normal;
width: 32px;
will-change: auto;
word-break: normal;
word-spacing: 0px;
writing-mode: horizontal-tb;
x: 0px;
y: 0px;
z-index: auto;
-moz-box-align: stretch;
-moz-box-direction: normal;
-moz-box-flex: 0;
-moz-box-ordinal-group: 1;
-moz-box-orient: horizontal;
-moz-box-pack: start;
-moz-float-edge: content-box;
-moz-force-broken-image-icon: 0;
-moz-image-region: auto;
-moz-orient: inline;
-moz-tab-size: 8;
-moz-text-size-adjust: auto;
-moz-user-focus: none;
-moz-user-input: auto;
-moz-user-modify: read-only;
-moz-window-dragging: default;
-webkit-line-clamp: none;
-webkit-text-fill-color: rgb(89, 89, 89);
-webkit-text-stroke-color: rgb(89, 89, 89);
-webkit-text-stroke-width: 0px;
}




.fondodiv {
align-content: normal;
align-items: normal;
align-self: auto;
animation-delay: 0s;
animation-direction: normal;
animation-duration: 0s;
animation-fill-mode: none;
animation-iteration-count: 1;
animation-name: none;
animation-play-state: running;
animation-timing-function: ease;
appearance: none;
aspect-ratio: auto;
backface-visibility: visible;
background-attachment: scroll;
background-blend-mode: normal;
background-clip: border-box;
background-color: rgb(237, 246, 255);
background-image: none;
background-origin: padding-box;
background-position: 0% 0%;
background-position-x: 0%;
background-position-y: 0%;
background-repeat: repeat;
background-size: auto;
block-size: 274px;
border-block-end-color: rgb(133, 192, 200);
border-block-end-style: solid;
border-block-end-width: 1px;
border-block-start-color: rgb(133, 192, 200);
border-block-start-style: solid;
border-block-start-width: 1px;
border-bottom-color: rgb(133, 192, 200);
border-bottom-left-radius: 3px;
border-bottom-right-radius: 3px;
border-bottom-style: solid;
border-bottom-width: 1px;
border-collapse: separate;
border-end-end-radius: 3px;
border-end-start-radius: 3px;
border-image-outset: 0;
border-image-repeat: stretch;
border-image-slice: 100%;
border-image-source: none;
border-image-width: 1;
border-inline-end-color: rgb(133, 192, 200);
border-inline-end-style: solid;
border-inline-end-width: 1px;
border-inline-start-color: rgb(133, 192, 200);
border-inline-start-style: solid;
border-inline-start-width: 1px;
border-left-color: rgb(133, 192, 200);
border-left-style: solid;
border-left-width: 1px;
border-right-color: rgb(133, 192, 200);
border-right-style: solid;
border-right-width: 1px;
border-spacing: 0px 0px;
border-start-end-radius: 3px;
border-start-start-radius: 3px;
border-top-color: rgb(133, 192, 200);
border-top-left-radius: 3px;
border-top-right-radius: 3px;
border-top-style: solid;
border-top-width: 1px;
bottom: auto;
box-decoration-break: slice;
box-shadow: none;
box-sizing: content-box;
break-after: auto;
break-before: auto;
break-inside: auto;
caption-side: top;
caret-color: rgb(89, 89, 89);
clear: both;
clip: auto;
clip-path: none;
clip-rule: nonzero;
color: rgb(89, 89, 89);
color-adjust: economy;
color-interpolation: srgb;
color-interpolation-filters: linearrgb;
column-count: auto;
column-fill: balance;
column-gap: normal;
column-rule-color: rgb(89, 89, 89);
column-rule-style: none;
column-rule-width: 0px;
column-span: none;
column-width: auto;
contain: none;
content: normal;
counter-increment: none;
counter-reset: none;
counter-set: none;
cursor: auto;
cx: 0px;
cy: 0px;
direction: ltr;
display: block;
dominant-baseline: auto;
empty-cells: show;
fill: rgb(0, 0, 0);
fill-opacity: 1;
fill-rule: nonzero;
filter: none;
flex-basis: auto;
flex-direction: row;
flex-grow: 0;
flex-shrink: 1;
flex-wrap: nowrap;
float: none;
flood-color: rgb(0, 0, 0);
flood-opacity: 1;
font-family: Arial, "Helvetica Neue", Helvetica, sans-serif;
font-feature-settings: normal;
font-kerning: auto;
font-language-override: normal;
font-optical-sizing: auto;
font-size: 12px;
font-size-adjust: none;
font-stretch: 100%;
font-style: normal;
font-synthesis: weight style;
font-variant: normal;
font-variant-alternates: normal;
font-variant-caps: normal;
font-variant-east-asian: normal;
font-variant-ligatures: normal;
font-variant-numeric: normal;
font-variant-position: normal;
font-variation-settings: normal;
font-weight: 400;
grid-auto-columns: auto;
grid-auto-flow: row;
grid-auto-rows: auto;
grid-column-end: auto;
grid-column-start: auto;
grid-row-end: auto;
grid-row-start: auto;
grid-template-areas: none;
grid-template-columns: none;
grid-template-rows: none;
height: 274px;
hyphens: manual;
image-orientation: from-image;
image-rendering: auto;
ime-mode: auto;
inline-size: 878px;
inset-block-end: auto;
inset-block-start: auto;
inset-inline-end: auto;
inset-inline-start: auto;
isolation: auto;
justify-content: normal;
justify-items: normal;
justify-self: auto;
left: auto;
letter-spacing: normal;
lighting-color: rgb(255, 255, 255);
line-break: auto;
line-height: normal;
list-style-image: none;
list-style-position: outside;
list-style-type: disc;
margin-block-end: 20px;
margin-block-start: 10px;
margin-bottom: 20px;
margin-inline-end: 20px;
margin-inline-start: 20px;
margin-left: 20px;
margin-right: 20px;
margin-top: 10px;
marker-end: none;
marker-mid: none;
marker-start: none;
mask: none;
mask-clip: border-box;
mask-composite: add;
mask-image: none;
mask-mode: match-source;
mask-origin: border-box;
mask-position: 0% 0%;
mask-position-x: 0%;
mask-position-y: 0%;
mask-repeat: repeat;
mask-size: auto;
mask-type: luminance;
max-block-size: none;
max-height: none;
max-inline-size: none;
max-width: none;
min-block-size: 0px;
min-height: 0px;
min-inline-size: 0px;
min-width: 0px;
mix-blend-mode: normal;
object-fit: fill;
object-position: 50% 50%;
offset-anchor: auto;
offset-distance: 0px;
offset-path: none;
offset-rotate: auto;
opacity: 1;
order: 0;
outline-color: rgb(89, 89, 89);
outline-offset: 0px;
outline-style: none;
outline-width: 0px;
overflow: visible;
overflow-anchor: auto;
overflow-block: visible;
overflow-inline: visible;
overflow-wrap: normal;
overflow-x: visible;
overflow-y: visible;
overscroll-behavior-block: auto;
overscroll-behavior-inline: auto;
overscroll-behavior-x: auto;
overscroll-behavior-y: auto;
padding-block-end: 20px;
padding-block-start: 20px;
padding-bottom: 20px;
padding-inline-end: 20px;
padding-inline-start: 20px;
padding-left: 20px;
padding-right: 20px;
padding-top: 20px;
page-break-after: auto;
page-break-before: auto;
paint-order: normal;
perspective: none;
perspective-origin: 460px 158px;
pointer-events: auto;
position: static;
quotes: auto;
r: 0px;
resize: none;
right: auto;
rotate: none;
row-gap: normal;
ruby-align: space-around;
ruby-position: alternate;
rx: auto;
ry: auto;
scale: none;
scroll-behavior: auto;
scroll-margin-block-end: 0px;
scroll-margin-block-start: 0px;
scroll-margin-bottom: 0px;
scroll-margin-inline-end: 0px;
scroll-margin-inline-start: 0px;
scroll-margin-left: 0px;
scroll-margin-right: 0px;
scroll-margin-top: 0px;
scroll-padding-block-end: auto;
scroll-padding-block-start: auto;
scroll-padding-bottom: auto;
scroll-padding-inline-end: auto;
scroll-padding-inline-start: auto;
scroll-padding-left: auto;
scroll-padding-right: auto;
scroll-padding-top: auto;
scroll-snap-align: none;
scroll-snap-type: none;
scrollbar-color: auto;
scrollbar-width: auto;
shape-image-threshold: 0;
shape-margin: 0px;
shape-outside: none;
shape-rendering: auto;
stop-color: rgb(0, 0, 0);
stop-opacity: 1;
stroke: none;
stroke-dasharray: none;
stroke-dashoffset: 0px;
stroke-linecap: butt;
stroke-linejoin: miter;
stroke-miterlimit: 4;
stroke-opacity: 1;
stroke-width: 1px;
table-layout: auto;
text-align: start;
text-align-last: auto;
text-anchor: start;
text-combine-upright: none;
text-decoration: rgb(89, 89, 89);
text-decoration-color: rgb(89, 89, 89);
text-decoration-line: none;
text-decoration-skip-ink: auto;
text-decoration-style: solid;
text-decoration-thickness: auto;
text-emphasis-color: rgb(89, 89, 89);
text-emphasis-position: over right;
text-emphasis-style: none;
text-indent: 0px;
text-justify: auto;
text-orientation: mixed;
text-overflow: clip;
text-rendering: auto;
text-shadow: none;
text-transform: none;
text-underline-offset: auto;
text-underline-position: auto;
top: auto;
touch-action: auto;
transform: none;
transform-box: border-box;
transform-origin: 460px 158px;
transform-style: flat;
transition-delay: 0s;
transition-duration: 0s;
transition-property: all;
transition-timing-function: ease;
translate: none;
unicode-bidi: isolate;
user-select: auto;
vector-effect: none;
vertical-align: baseline;
visibility: visible;
white-space: normal;
width: 878px;
will-change: auto;
word-break: normal;
word-spacing: 0px;
writing-mode: horizontal-tb;
x: 0px;
y: 0px;
z-index: auto;
-moz-box-align: stretch;
-moz-box-direction: normal;
-moz-box-flex: 0;
-moz-box-ordinal-group: 1;
-moz-box-orient: horizontal;
-moz-box-pack: start;
-moz-float-edge: content-box;
-moz-force-broken-image-icon: 0;
-moz-image-region: auto;
-moz-orient: inline;
-moz-tab-size: 8;
-moz-text-size-adjust: auto;
-moz-user-focus: none;
-moz-user-input: auto;
-moz-user-modify: read-only;
-moz-window-dragging: default;
-webkit-line-clamp: none;
-webkit-text-fill-color: rgb(89, 89, 89);
-webkit-text-stroke-color: rgb(89, 89, 89);
-webkit-text-stroke-width: 0px;
}


.entrada {
align-content: normal;
align-items: normal;
align-self: auto;
animation-delay: 0s;
animation-direction: normal;
animation-duration: 0s;
animation-fill-mode: none;
animation-iteration-count: 1;
animation-name: none;
animation-play-state: running;
animation-timing-function: ease;
appearance: none;
aspect-ratio: auto;
backface-visibility: visible;
background-attachment: scroll;
background-blend-mode: normal;
background-clip: border-box;
background-color: rgb(238, 238, 238);
background-image: none;
background-origin: padding-box;
background-position: 0% 0%;
background-position-x: 0%;
background-position-y: 0%;
background-repeat: repeat;
background-size: auto;
/* block-size: 76px; */
border-block-end-color: rgb(174, 198, 207);
border-block-end-style: solid;
border-block-end-width: 1px;
border-block-start-color: rgb(174, 198, 207);
border-block-start-style: solid;
border-block-start-width: 1px;
border-bottom-color: rgb(174, 198, 207);
border-bottom-left-radius: 0px;
border-bottom-right-radius: 0px;
border-bottom-style: solid;
border-bottom-width: 1px;
border-collapse: separate;
border-end-end-radius: 0px;
border-end-start-radius: 0px;
border-image-outset: 0;
border-image-repeat: stretch;
border-image-slice: 100%;
border-image-source: none;
border-image-width: 1;
border-inline-end-color: rgb(174, 198, 207);
border-inline-end-style: solid;
border-inline-end-width: 1px;
border-inline-start-color: rgb(174, 198, 207);
border-inline-start-style: solid;
border-inline-start-width: 1px;
border-left-color: rgb(174, 198, 207);
border-left-style: solid;
border-left-width: 1px;
border-right-color: rgb(174, 198, 207);
border-right-style: solid;
border-right-width: 1px;
border-spacing: 0px 0px;
border-start-end-radius: 0px;
border-start-start-radius: 0px;
border-top-color: rgb(174, 198, 207);
border-top-left-radius: 0px;
border-top-right-radius: 0px;
border-top-style: solid;
border-top-width: 1px;
bottom: 0px;
box-decoration-break: slice;
box-shadow: none;
box-sizing: border-box;
break-after: auto;
break-before: auto;
break-inside: auto;
caption-side: top;
caret-color: rgb(89, 89, 89);
clear: none;
clip: auto;
clip-path: none;
clip-rule: nonzero;
color: rgb(89, 89, 89);
color-adjust: economy;
color-interpolation: srgb;
color-interpolation-filters: linearrgb;
column-count: auto;
column-fill: balance;
column-gap: normal;
column-rule-color: rgb(89, 89, 89);
column-rule-style: none;
column-rule-width: 0px;
column-span: none;
column-width: auto;
contain: none;
content: normal;
counter-increment: none;
counter-reset: none;
counter-set: none;
cursor: auto;
cx: 0px;
cy: 0px;
direction: ltr;
display: block;
dominant-baseline: auto;
empty-cells: show;
fill: rgb(0, 0, 0);
fill-opacity: 1;
fill-rule: nonzero;
filter: none;
flex-basis: auto;
flex-direction: row;
flex-grow: 0;
flex-shrink: 1;
flex-wrap: nowrap;
float: none;
flood-color: rgb(0, 0, 0);
flood-opacity: 1;
font-family: Arial, "Helvetica Neue", Helvetica, sans-serif;
font-feature-settings: normal;
font-kerning: auto;
font-language-override: normal;
font-optical-sizing: auto;
font-size: 12px;
font-size-adjust: none;
font-stretch: 100%;
font-style: normal;
font-synthesis: weight style;
font-variant: normal;
font-variant-alternates: normal;
font-variant-caps: normal;
font-variant-east-asian: normal;
font-variant-ligatures: normal;
font-variant-numeric: normal;
font-variant-position: normal;
font-variation-settings: normal;
font-weight: 400;
grid-auto-columns: auto;
grid-auto-flow: row;
grid-auto-rows: auto;
grid-column-end: auto;
grid-column-start: auto;
grid-row-end: auto;
grid-row-start: auto;
grid-template-areas: none;
grid-template-columns: none;
grid-template-rows: none;
height: 76px;
hyphens: manual;
image-orientation: from-image;
image-rendering: auto;
ime-mode: auto;
inline-size: 395.1px;
inset-block-end: 0px;
inset-block-start: 0px;
inset-inline-end: 0px;
inset-inline-start: 0px;
isolation: auto;
justify-content: normal;
justify-items: normal;
justify-self: auto;
left: 0px;
letter-spacing: normal;
lighting-color: rgb(255, 255, 255);
line-break: auto;
line-height: 20px;
list-style-image: none;
list-style-position: outside;
list-style-type: disc;
margin-block-end: 0px;
margin-block-start: 0px;
margin-bottom: 0px;
margin-inline-end: 0px;
margin-inline-start: 0px;
margin-left: 0px;
margin-right: 30px;
margin-top: 0px;
marker-end: none;
marker-mid: none;
marker-start: none;
mask: none;
mask-clip: border-box;
mask-composite: add;
mask-image: none;
mask-mode: match-source;
mask-origin: border-box;
mask-position: 0% 0%;
mask-position-x: 0%;
mask-position-y: 0%;
mask-repeat: repeat;
mask-size: auto;
mask-type: luminance;
max-block-size: none;
max-height: none;
max-inline-size: none;
max-width: none;
min-block-size: 64px;
min-height: 64px;
min-inline-size: 0px;
min-width: 0px;
mix-blend-mode: normal;
object-fit: fill;
object-position: 50% 50%;
offset-anchor: auto;
offset-distance: 0px;
offset-path: none;
offset-rotate: auto;
opacity: 1;
order: 0;
outline-color: rgb(89, 89, 89);
outline-offset: 0px;
outline-style: none;
outline-width: 0px;
overflow: visible;
overflow-anchor: auto;
overflow-block: visible;
overflow-inline: visible;
overflow-wrap: normal;
overflow-x: visible;
overflow-y: visible;
overscroll-behavior-block: auto;
overscroll-behavior-inline: auto;
overscroll-behavior-x: auto;
overscroll-behavior-y: auto;
padding-block-end: 12px;
padding-block-start: 12px;
padding-bottom: 12px;
padding-inline-end: 12px;
padding-inline-start: 12px;
padding-left: 12px;
padding-right: 12px;
padding-top: 12px;
page-break-after: auto;
page-break-before: auto;
paint-order: normal;
perspective: none;
perspective-origin: 197.55px 38px;
pointer-events: auto;
position: relative;
quotes: auto;
r: 0px;
resize: none;
right: 0px;
rotate: none;
row-gap: normal;
ruby-align: space-around;
ruby-position: alternate;
rx: auto;
ry: auto;
scale: none;
scroll-behavior: auto;
scroll-margin-block-end: 0px;
scroll-margin-block-start: 0px;
scroll-margin-bottom: 0px;
scroll-margin-inline-end: 0px;
scroll-margin-inline-start: 0px;
scroll-margin-left: 0px;
scroll-margin-right: 0px;
scroll-margin-top: 0px;
scroll-padding-block-end: auto;
scroll-padding-block-start: auto;
scroll-padding-bottom: auto;
scroll-padding-inline-end: auto;
scroll-padding-inline-start: auto;
scroll-padding-left: auto;
scroll-padding-right: auto;
scroll-padding-top: auto;
scroll-snap-align: none;
scroll-snap-type: none;
scrollbar-color: auto;
scrollbar-width: auto;
shape-image-threshold: 0;
shape-margin: 0px;
shape-outside: none;
shape-rendering: auto;
stop-color: rgb(0, 0, 0);
stop-opacity: 1;
stroke: none;
stroke-dasharray: none;
stroke-dashoffset: 0px;
stroke-linecap: butt;
stroke-linejoin: miter;
stroke-miterlimit: 4;
stroke-opacity: 1;
stroke-width: 1px;
table-layout: auto;
text-align: start;
text-align-last: auto;
text-anchor: start;
text-combine-upright: none;
text-decoration: rgb(89, 89, 89);
text-decoration-color: rgb(89, 89, 89);
text-decoration-line: none;
text-decoration-skip-ink: auto;
text-decoration-style: solid;
text-decoration-thickness: auto;
text-emphasis-color: rgb(89, 89, 89);
text-emphasis-position: over right;
text-emphasis-style: none;
text-indent: 0px;
text-justify: auto;
text-orientation: mixed;
text-overflow: clip;
text-rendering: auto;
text-shadow: none;
text-transform: none;
text-underline-offset: auto;
text-underline-position: auto;
top: 0px;
touch-action: auto;
transform: none;
transform-box: border-box;
transform-origin: 197.55px 38px;
transform-style: flat;
transition-delay: 0s;
transition-duration: 0s;
transition-property: all;
transition-timing-function: ease;
translate: none;
unicode-bidi: isolate;
user-select: auto;
vector-effect: none;
vertical-align: baseline;
visibility: visible;
white-space: normal;
min-width: 250px;
will-change: auto;
word-break: normal;
word-spacing: 0px;
writing-mode: horizontal-tb;
x: 0px;
y: 0px;
z-index: auto;
-moz-box-align: stretch;
-moz-box-direction: normal;
-moz-box-flex: 0;
-moz-box-ordinal-group: 1;
-moz-box-orient: horizontal;
-moz-box-pack: start;
-moz-float-edge: content-box;
-moz-force-broken-image-icon: 0;
-moz-image-region: auto;
-moz-orient: inline;
-moz-tab-size: 8;
-moz-text-size-adjust: auto;
-moz-user-focus: none;
-moz-user-input: auto;
-moz-user-modify: read-only;
-moz-window-dragging: default;
-webkit-line-clamp: none;
-webkit-text-fill-color: rgb(89, 89, 89);
-webkit-text-stroke-color: rgb(89, 89, 89);
-webkit-text-stroke-width: 0px;
}
--%>
</style>


<%--

<div  class="fondodiv">
<table style="padding: 0px;" border="0" cellpadding="0" cellspacing="0">

<tr>

<td><div class="entrada"> Tramitador bla bla bla&nbsp; </div></td>
<td>

<!--  =============  INICI ===============   -->
<table style="padding: 0px; height: 100%;" border="0"  cellpadding="0" cellspacing="0">
<tr>
<td>&nbsp;</td>
<td style="width: 3px; background-color: #CCCCCC" width="3px"></td>
<td>&nbsp;</td>
</tr>
<tr>
<td colspan="3">
<div class="cercle" style="border: 4px solid #ff0000;"></div>
</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>&nbsp;</td>
<td style="width:3px; background-color: #CCCCCC" width="3px"></td>
<td>&nbsp;</td>
</tr>
</table>
<!--  ============================   -->



</td>
<td>Data: 12-23-3456 12:34 <br/> <i>anadal</i></td>

</tr>

</table>
</div>
 --%>



<%--  style="background-image: url('<c:url value="/img/caibbg.png"/>');" --%>

<div id="container" style="background-color: #FFFFFF" >

    <div id="wrap" style="background-color: #FFFFFF">

        
        <div id="mainWindow" class="shadow2 overflowh sandboxd w960"  >

            
            <div id="contentData" class="p20">





<div class="full-width" style="background-color: #EEEEEE">
<%-- 
                    <h4>${titol}</h4>--%>

                    <h3>Número: ${ID}</h3>
                    <br> Estat: <strong class="label-custom">${estat}</strong>
                    <hr>
                    Nom Contacte: ${personaContacte}<br>
                    Email Contacte: ${personaContacteEmail}<br>
                    

                </div>

                <div class="container">


                    <c:forEach var="event" items="${eventItems}">

                        <c:set var="isPublic" value="${event.tipus > 0}" />
                        <c:set var="isContacte" value="${event.tipus == 2}" />

                        <c:set var="show" value="true" />

                        <c:if test="${showOnlyPublic==true && isPublic == false }">
                            <c:set var="show" value="false" />
                        </c:if>


                        <c:if test="${show}">
                        
                        
                        <c:set var="background" value="#cce6ff" />
                        <c:set var="border" value="#F0E68C" />
        
                        <c:if test="${!isPublic}">
        
                            <c:set var="background" value="#ffb3b3" />
                            <c:set var="border" value="#ff0000" />
        
                        </c:if>
                        
                        <c:if test="${isContacte}">
                            <c:set var="background" value="#ccffcc" />
                            <c:set var="border" value="#9ACD32" />
        
                        </c:if>
                        
                        

                            <c:if test="${isContacte}">
                                <div
                                    class="timeline  timeline-left timeline-with-arrows blue-blue-blue">
                                    <div class="timeline-block">
                                        <div class="timeline-icon"
                                            style="border: 4px solid ${border};"></div>
                                        <div class="timeline-content">

                                            <div style="white-space2: pre-wrap;">

                                                <%@ include
                                                    file="/WEB-INF/jsp/common/eventscore.jsp"%>

                                            </div>
                                            <div class="timeline-date"
                                                style="left: -190px;">
                                                ${event.dataEvent}<br> <span
                                                    style="font-weight: bold; font-size: 10pt">De
                                                    ${event.persona}</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:if>

                            <c:if test="${!isContacte}">
                                <div class="timeline timeline-with-arrows blue-blue-blue">
                                    <div class="timeline-block">
                                        <div class="timeline-icon"
                                            style="border: 4px solid ${border};"></div>
                                        <div class="timeline-content">

                                            <div style="white-space2: pre-wrap;">


                                                <%@ include
                                                    file="/WEB-INF/jsp/common/eventscore.jsp"%>

                                                <div class="timeline-date">
                                                    ${event.dataEvent}<br> <span
                                                        style="font-weight: bold; font-size: 10pt">De
                                                        ${event.persona}</span>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                            </c:if>


                        </c:if>

                    </c:forEach>

                </div>
                </div>
                
                </div>
    </div>
    </div>
    
    <%-- 
                <form id="formularioMensaje" name="formularioMensaje"
                    action="/ayuda/seguimientoEnviarMensaje" method="post" class="centrd"
                    enctype="multipart/form-data">
                    <fieldset>
                        

                        <div class="fsb mb20 mr20 ml20">
                            <h4>Enviar un mensaje al tramitador</h4>
                            <h4></h4>
                        </div>
                        <div class="clearfix"></div>
                        <div class="fsb mb20 mr20 ml20" style="text-align: right">

                            <textarea style="width: 840px; height: 100px;" name="mensaje"
                                id="mensaje"></textarea>
                            <input style="visibility: hidden; display: none" type="file"
                                name="fichero" id="fichero">

                            <div style="height: 10px"></div>
                            <a style="margin-top: 5px;" id="boton_fichero"
                                class="pickfiles simbutton" href="javascript:;">Subir
                                un fichero</a> <input class="primary" type="submit"
                                value="Enviar">&nbsp;&nbsp;&nbsp;
                        </div>
                    </fieldset>
                </form>
        
       --%>
   <br/>
   <br/>
   
   <center><b>©Fundaci&oacute Bit - &Agrave;rea de Govern Digital - PinbalAdmin</b></center>
<%--
<div id="footer">
    <p>©Fundaci&oacute Bit - &Agrave;rea de Govern Digital - PinbalAdmin</p>
</div>



<div id="loading-ajax" style="display: none">
    <img alt="Cargando..." title="Cargando..." src="<c:url value="/ayuda/images/morfos/ajax-loader.gif"/>">
</div>


 
<div id="ui-datepicker-div"
    class="ui-datepicker ui-widget ui-widget-content ui-helper-clearfix ui-corner-all ui-helper-hidden-accessible"></div>
<div style="display: none; z-index: 1000; outline: currentcolor none 0px;"
    class="ui-dialog ui-widget ui-widget-content ui-corner-all  ui-draggable"
    tabindex="-1" role="dialog" aria-labelledby="ui-dialog-title-AjaxShow">
    <div class="ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix">
        <span class="ui-dialog-title" id="ui-dialog-title-AjaxShow">&nbsp;</span><a
            href="#" class="ui-dialog-titlebar-close ui-corner-all" role="button"><span
            class="ui-icon ui-icon-closethick">close</span></a>
    </div>
    <div id="AjaxShow" class="ui-dialog-content ui-widget-content"></div>
    
    --%>


</div>



