package com.github.shynixn.blockball.entity

import com.github.shynixn.blockball.api.business.enumeration.ChatClickAction
import com.github.shynixn.blockball.api.persistence.entity.ChatBuilder
import com.github.shynixn.blockball.api.persistence.entity.ChatBuilderComponent
import com.github.shynixn.mcutils.common.ChatColor
import com.github.shynixn.mcutils.common.translateChatColors

class ChatBuilderComponentEntity(private val builder: ChatBuilder, payloadtext: String) : ChatBuilderComponent {
    private val text = StringBuilder()
    private var clickAction: ChatClickAction? = null
    private var clickActionData: String? = null
    private var hoverActionData: ChatBuilderComponent? = null
    private var color: ChatColor? = null

    init {
        text.append(payloadtext)
    }

    /**
     * Gets the root builder of the component.
     */
    override fun builder(): ChatBuilder {
        return builder
    }

    /**
     * Sets the click action.
     */
    override fun setClickAction(clickAction: ChatClickAction, payload: String): ChatBuilderComponent {
        this.clickAction = clickAction
        this.clickActionData = payload
        return this
    }

    /**
     * Sets the hover text.
     */
    override fun setHoverText(text: String): ChatBuilderComponent {
        this.hoverActionData = ChatBuilderComponentEntity(this.builder, text)
        return this
    }

    /**
     * Sets the color of the text.
     */
    override fun setColor(color: ChatColor): ChatBuilderComponent {
        this.color = color
        return this
    }

    /**
     * Returns a string representation of the object. In general, the
     * `toString` method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     *
     * @return a string representation of the object.
     */
    override fun toString(): String {
        val builder = StringBuilder()
        builder.append("{ \"text\": \"")
        builder.append(this.text.toString().translateChatColors())
        builder.append('"')

        if (this.color != null) {
            builder.append(", \"color\": \"")
            builder.append(this.color!!.name.toLowerCase())
            builder.append('"')
        }

        if (this.clickAction != null) {
            builder.append(", \"clickEvent\": {\"action\": \"")
            builder.append(this.clickAction!!.name.toLowerCase())
            builder.append("\" , \"value\" : \"")
            builder.append(this.clickActionData)
            builder.append("\"}")
        }

        if (this.hoverActionData != null) {
            builder.append(", \"hoverEvent\": {\"action\": \"")
            builder.append("show_text")
            builder.append("\" , \"value\" : ")
            builder.append(this.hoverActionData.toString())
            builder.append('}')
        }

        builder.append('}')
        return builder.toString()
    }
}
