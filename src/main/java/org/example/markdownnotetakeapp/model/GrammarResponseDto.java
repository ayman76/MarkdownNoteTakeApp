package org.example.markdownnotetakeapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.languagetool.rules.RuleMatch;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GrammarResponseDto {

    private String message;
    private int line;
    private int fromPos;
    private int toPos;
    private String ruleId;

    public GrammarResponseDto(RuleMatch ruleMatch) {
        this.message = ruleMatch.getMessage();
        this.fromPos =ruleMatch.getFromPos();
        this.toPos =ruleMatch.getToPos();
        this.ruleId = ruleMatch.getRule().getId();
    }
}
