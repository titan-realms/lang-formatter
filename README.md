# lang-formatter

This is our API/library that parses our language spec from lang files into objects. These are then distributed across our servers to deliver dynamic messages to players on demand.

This library is designed to render into Adventure Componenets.

NOTE: We will not provide any support for this project. Its development is completely internal and it's only open sourced as we thought others may find it useful. Only issues related to bugs will be dealt with.

## Spec

`{#hex}` = hex colour code<br/>
`{!obfuscated/bold/strikethrough/underlined/italic}` = style codes<br/>
`{@placeholder}` = placeholder rendered when converted into component<br/>
`{"placeholder" > 1 ? "true text" : "false text"}` = conditional text using a placeholder rendered when converted into component

## Examples

__Ice Cream__

{#00000}{!bold}get your {#ffffff}{@ice-cream-limit} {#00000}free ice cream{"ice-cream-limit" > 1 ? "s" : ""}

 - The entire text will be bold
 - `get your ` will be black
 - `{#ffffff}{@ice-cream-limit}` will become white and will display their ice cream limit.
 - The remaining text will be black.
 - If ice-cream-limit is > 1, an `s` will be appended onto the end. Otherwise, nothing will be appended.
 
Example output 1: `get your 10 free ice creams`<br/>
Example output 2: `get your 1 free ice cream`
