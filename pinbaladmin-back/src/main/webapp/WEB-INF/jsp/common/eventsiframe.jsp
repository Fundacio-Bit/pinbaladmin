<%@page import="org.fundaciobit.genapp.common.web.html.IconUtils"%>
<%@ page contentType="text/html; charset=UTF-8" language="java"%><%@ include
	file="/WEB-INF/jsp/moduls/includes.jsp"%>

<style>
.mainwindow {
	background-color: rgb(222, 233, 244);
	border-bottom-color: rgb(255, 255, 255);
	border-bottom-left-radius: 7px;
	border-bottom-right-radius: 7px;
	border-bottom-style: solid;
	border-bottom-width: 3px;
	border-image-outset: 0;
	border-image-repeat: stretch;
	border-image-slice: 100%;
	border-image-source: none;
	border-image-width: 1;
	border-left-color: rgb(255, 255, 255);
	border-left-style: solid;
	border-left-width: 3px;
	border-right-color: rgb(255, 255, 255);
	border-right-style: solid;
	border-right-width: 3px;
	border-top-color: rgb(255, 255, 255);
	border-top-left-radius: 7px;
	border-top-right-radius: 7px;
	border-top-style: solid;
	border-top-width: 3px;
	box-shadow: rgba(0, 0, 0, 0.4) 1px 2px 5px 0px;
	color: rgb(89, 89, 89);
	font-family: Arial, "Helvetica Neue", Helvetica, sans-serif;
	font-size: 12px;
	margin-bottom: 5px;
	margin-left: 5px;
	margin-right: 5px;
	margin-top: 5px;
	overflow: hidden;
	overflow-x: hidden;
	overflow-y: hidden;
	width: auto;
	height: inherit;
}

.fondodiv2 {
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
	margin-block-end: 20px;
	margin-block-start: 10px;
	margin-bottom: 20px;
	margin-inline-end: 20px;
	margin-inline-start: 20px;
	margin-left: 20px;
	margin-right: 20px;
	margin-top: 10px;
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
	overflow: auto;
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
	/* block-size: 274px; */
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
	/* height: 274px; */
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
	/* height: 76px; */
	hyphens: manual;
	image-orientation: from-image;
	image-rendering: auto;
	ime-mode: auto;
	/* inline-size: 395.1px; */
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
	min-width: 250px;
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
	/*block-size: 32px;*/
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

textarea.event {
	background-color: rgb(238, 238, 238) !important;
	border: none;
	resize: none;
}

.windowSubtitle {
	margin: 0;
}
</style>

<!--  Missatges  -->
<jsp:include page="/WEB-INF/jsp/moduls/missatges.jsp" />


<div class="mainwindow">

	<div id="fondodiv1" style="display: flex; margin-top: 1rem;">
		<img src="/pinbaladmin/img/caibbg.png"
			style="align-self: center; margin: 1rem 0.5rem;">
		<table width="100%">
			<tr>
				<td>
					<h4 class="windowSubtitle">${tipus}</h4>
				</td>

				<td align="right">
					<div id="botonera_div" style="margin-right: 1rem;">
						<c:url var="theurlnew" value="${contextweb}/new" />
						<c:set var="urlnou"
							value="javascript:document.location.href='${theurlnew}'" />

						<c:if test="${not isEstatal}">
							<c:if test="${empty personaContacteEmail}">
								<c:set var="urlnou"
									value="javascript:alert('La sol·licitud no te definit el correu del contacte EF');" />
							</c:if>
						</c:if>

						<c:if test="${!isPublic}">

							<a class="btn btn-sm btn-warning" role="button"
								href="<c:url value="${contextweb}/enviarcorreu/${ID}"/>"> <i
								class="<%=IconUtils.ICON_ENVELOPE%>"></i> Enviar Correu al
								Contacte
							</a>

							<a class="btn btn-sm btn-success" role="button"
								href="<c:url value="javascript:changeOperador()"/>"> <i
								class="<%=IconUtils.ICON_RELOAD%>"></i> Canviar l'operador
							</a>

							<c:if test="${isSolicitud}">
								<a class="btn btn-info btn-sm" role="button"
									href="<c:url value="/operador/solicitudfullview/view/${ID}"/>">
									<i class="<%=IconUtils.ICON_EYE%>"></i> Veure ${tipus} (FULL)
								</a>
							</c:if>
							
							<c:if test="${!isSolicitud}">
								<a class="btn btn-info btn-sm" role="button"
									href="<c:url value="${urlToEditItem}"/>"> <i
									class="<%=IconUtils.ICON_EYE%>"></i> Editar ${tipus}
								</a>
							</c:if>

							<c:if test="${not empty urlToCloseItem}">
								<a class="btn btn-danger btn-sm" role="button"
									href="<c:url value="${urlToCloseItem}"/>"> <i
									class="fas fa-window-close"></i> Tancar ${tipus}
								</a>
							</c:if>


							<c:if test="${not empty urlMarcarComNoLlegides}">
								<a class="btn btn-warning btn-sm" role="button"
									href="${urlMarcarComNoLlegides}"> <i
									class="fas fa-eye-slash"></i> Marcar Entrades com NO a Llegides
								</a>
							</c:if>
							<c:if test="${not empty urlMarcarComLlegides}">
								<a class="btn btn-success btn-sm" role="button"
									href="${urlMarcarComLlegides}"> <i
									class="<%=IconUtils.ICON_CHECK%>"></i> Marcar Entrades com a
									Llegides
								</a>
							</c:if>

						</c:if>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<h4 class="windowTitle" style="margin: 0; padding-top: 0.5rem;">${titol}</h4>
				</td>
			</tr>
		</table>
	</div>

	<div class="full-width"
		style="background-color: #EEEEEE; margin: 10px; padding: 5px;">
		<table style="width: 100%; font-size: 1.4em">
			<tbody>
				<tr>
					<td width="50%">
					   Número: <strong> ${ID}</strong><br /> 
                       Creador: <strong>${creador}</strong><br />
					   Data Creaci&oacute;: <strong class="label-custom">${datacreacio}</strong><br />
					</td>
					<td>
                       Estat: <strong class="label-custom">${estat}</strong><br /> 
					   Nom Contacte: <strong>${personaContacte}</strong><br />
					   Email Contacte: <strong>${personaContacteEmail}</strong> <br />

						<c:if test="${isSolicitud}">
						   Codi Procediment: <strong>${procedimentCodi}</strong>
						</c:if>

					</td>
				</tr>
			</tbody>
		</table>
	</div>

<style>
.line {
	height: 7rem;
	width: 3px;
	background-color: #CCCCCC;
	border-radius: 5px;
	margin-left: -17px;
}

.circle {
bo
									style="border: 4px solid ${border}; height: 32px; z-index: 2;"
}
</style>



	<div class="fondodiv2">
		<table style="padding: 0px;margin: auto;" border="0" cellpadding="0"
			cellspacing="0">

			<c:forEach var="event" items="${eventItems}">

				<c:set var="isPublicEvent"
					value="${(event.tipus > 0) && (event.tipus != 3)}" />
				<c:set var="isContacte" value="${event.tipus == 2}" />
				<c:set var="isCedent"
					value="${(event.tipus==3) || (event.tipus==-3)}" />

				<c:set var="show" value="${true}" />


				<c:if test="${showOnlyPublic==true}">

					<c:if test="${showOnlyPublic==true && isPublicEvent==false}">
						<c:set var="show" value="${false}" />
					</c:if>

					<c:if test="${(not empty cedent)}">
						<c:if test="${ isCedent && (event.persona eq cedent)}">
							<c:set var="show" value="${true}" />
						</c:if>
						<c:if test="${ not isCedent || (event.persona ne cedent)}">
							<c:set var="show" value="${false}" />
						</c:if>
					</c:if>
				</c:if>


				<c:if test="${show}">

					<c:set var="mostrarMissatgeDreta"
						value="${event.tipus == 2 || event.tipus==3}" />

					<c:set var="background" value="#cce6ff" />
					<c:set var="border" value="#F0E68C" />

					<c:if test="${!isPublic}">
						<c:set var="background" value="#ffb3b3" />
						<c:set var="border" value="#ff0000" />
						<c:set var="title" value="Privat" />
					</c:if>

					<c:if test="${isContacte}">
						<c:set var="background" value="#ccffcc" />
						<c:set var="border" value="#9ACD32" />
						<c:set var="title" value="Public" />
					</c:if>

					<c:if test="${event.tipus == 1}">
						<c:set var="background" value="#0000FF" />
						<c:set var="border" value="#0000FF" />
						<c:set var="title" value="Compartit" />
					</c:if>

					<c:if test="${isCedent}">
						<c:set var="border" value="#FF8C00" />
						<c:set var="background" value="#FF8C00" />
						<c:set var="title" value="Cedent" />
					</c:if>
					<tr>
						<td align="right" width="45%"><c:if
								test="${mostrarMissatgeDreta}">
								<div style="margin-right: 20px">
									${event.dataEvent}<br> <span
										style="font-weight: bold; font-size: 10pt"> De
										${event.persona} </span>
								</div>
							</c:if> <c:if test="${!mostrarMissatgeDreta}">
								<div style="margin: 10px 10px 24px 10px">
									<div class="entrada">
										<%@ include file="/WEB-INF/jsp/common/eventscore.jsp"%>
									</div>
								</div>
							</c:if></td>
						<td>
							<!--  =============  CERCLE ===============   -->
							<div style="display: flex; align-items: center;">
								<div class="cercle" style="border-color: ${border}; z-index: 2;"
									title="${title}"></div>
								<div class="line" title="${title}"></div>
							</div>
						</td>
						<!--  ============================   -->

						<%-- <td>
							<table style="padding: 0px; min-height: 100px" border="0">
								<tr>
									<td>&nbsp;</td>
									<td style="width: 3px; background-color: #CCCCCC" width="3px"></td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td colspan="3" height="32px" style="height: 32px;">
										<div class="cercle" style="border: 4px solid ${border};"
											title="${title}"></div>
									</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
									<td style="width: 3px; background-color: #CCCCCC" width="3px"></td>
									<td>&nbsp;</td>
								</tr>
							</table>
						</td> --%>
							<!--  ============================   -->
							
							
							
							
							
							
							

						<td width="45%">
							<div style="margin-left: 20px">

								<c:if test="${!mostrarMissatgeDreta}">
                                     ${event.dataEvent}<br />
									<span style="font-weight: bold; font-size: 10pt">
										<c:if test="${(event.tipus==-3)}">
	                                       De ${operador}  enviat a ${event.persona}
	                                    </c:if> 
	                                    <c:if test="${(event.tipus!=-3)}">
	                                       De ${event.persona} 
		                                   <c:if test="${(not empty event.destinatari && event.tipus == 1)}">
		                                      a ${event.destinatari} 
		                                      <c:if test="${(not empty event.destinatarimail)}">
                                                (${event.destinatarimail})
                                              </c:if> 
		                                   </c:if> 
                                    </c:if>
									</span>
								</c:if>







								<c:if test="${mostrarMissatgeDreta}">
									<div style="margin: 10px 10px 24px 10px">
										<div class="entrada">
											<%@ include file="/WEB-INF/jsp/common/eventscore.jsp"%>
										</div>
									</div>
								</c:if>
							</div>
						</td>

					</tr>
				</c:if>

			</c:forEach>
		</table>
	</div>
	<center>
		<iframe id="nou_event_id" style="border: 1px solid #ddd;" width="95%"
			height="${isPublic?450:560}px" onload="carregatIframe(this)"
			src="${theurlnew}"></iframe>
	</center>
	<br />
</div>
<br />

<script>

   var count = 0;

   function carregatIframe() {
       if (count != 0) {
         console.log('loaded iframe ');
         //alert("Carregat iframe ");
         window.top.location.href='<%=request.getContextPath()%>
    ${contextweb}/list';
        }
        count++;
    }
   
   
   window.onload = function() {
	    var textareas =  document.getElementsByTagName("textarea");
	      for ( let i = 0; i < textareas.length; i++ ) {
	        var textarea = textareas[i];
	            textarea.style.height = textarea.scrollHeight + "px";
	      }
	}

</script>


<br />

<center>
	<b>©Fundaci&oacute; Bit - &Agrave;rea de Govern Digital -
		PinbalAdmin</b>
</center>




<!-- Modal -->
<div class="modal fade" id="modelSeleccioTramitador" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Crear nova incidencia</h4>
				<button type="button" class="close"
					style="margin: 0px; padding: 0px" data-dismiss="modal">&times;</button>
			</div>
			<div class="modal-body">
				<%
				request.setAttribute("currentuser", request.getRemoteUser());
				%>

				<label id="label_operador" for="operador"> Selecciona
					operador: </label> <select id="operador" class="my_select">
					<c:forEach items="${operadors}" var="operador">
						<option value="${operador.key}"
							${(currentuser eq operador.key)?'selected':''}>${operador.value}</option>
					</c:forEach>
				</select>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" onClick="myFunction()"
					data-dismiss="modal">Ok</button>
			</div>
		</div>

	</div>
</div>

<script>
function myFunction() {
	
    var operador = document.getElementById("operador").value;
        
    var root = "<%=request.getContextPath()%>${urlToChangeOperator}";
    
    window.location.href = root
            + "/" + operador;
}

function changeOperador() {
    $("#modelSeleccioTramitador").modal();
}



</script>